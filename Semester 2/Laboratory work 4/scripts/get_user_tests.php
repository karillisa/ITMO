<?php
require_once __DIR__."/gates.php";
require_once __DIR__."/db.php";
include __DIR__."/get_professions_with_pvk.php";

function getAllTestsResults($category_id){
    $pdo = getPDO();
    $sql = "SELECT * FROM tests WHERE tests.category_id = $category_id";
    $result = $pdo->query($sql);
    $tests = $result->fetchAll(PDO::FETCH_ASSOC);
    foreach($tests as $key => $test){
        $result = $pdo->query("SELECT * FROM user_test_results WHERE user_test_results.test_id = {$test['id']}");
        $tests[$key]['results'] = $result->fetchAll(PDO::FETCH_ASSOC);
    }
    return $tests;
}

function getTestsForUser($id, $category_id){
    $pdo = getPDO();
    if($category_id == 4) {
        $sql = "SELECT * FROM user_tests, tests WHERE 
                                    user_tests.user_id = $id AND 
                                    user_tests.test_id = tests.id  AND
                                    tests.category_id = $category_id
                                    ORDER BY user_tests.order_for_user";
    } else if($category_id == 0){
        $sql = "SELECT * FROM tests";
    }
    else {
        $sql = "SELECT * FROM tests WHERE category_id = $category_id";
    }
    $result = $pdo->query($sql);
    $tests = $result->fetchAll(PDO::FETCH_ASSOC);
    foreach($tests as $key => $test){
        $result = $pdo->query("SELECT * FROM user_test_results WHERE user_test_results.user_id = $id AND user_test_results.test_id = {$test['id']}");
        $tests[$key]['results'] = $result->fetchAll(PDO::FETCH_ASSOC);
    }
    return $tests;
}
function getTestsForCurrentUser($category_id){
    $id = getCurrentUser()['id'];
    return getTestsForUser($id, $category_id);
}

function getTestById($id){
    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM tests WHERE id = $id");
    $test = $result->fetch();
    return $test;
}

function getNextTestForCurrentUser($test_id){
    $pdo = getPDO();
    $pdo->setAttribute(PDO::MYSQL_ATTR_USE_BUFFERED_QUERY, true);
    $id = getCurrentUser()['id'];
    $result = $pdo->query("SELECT * FROM tests WHERE tests.id = $test_id");
    $current_test = $result->fetch(MYSQLI_ASSOC);
    if($current_test['category_id'] == 4) {
        $sql = "SELECT * FROM user_tests, tests WHERE user_tests.user_id = $id AND user_tests.test_id = tests.id ORDER BY user_tests.order_for_user";
    } else {
        $sql = "SELECT tests.*, tests.id As test_id FROM tests WHERE category_id = ".$current_test['category_id'];
    }
    $result = $pdo->query($sql);
    $tests = $result->fetchAll();
    foreach ($tests as $key => $test) {
        if($test['test_id'] == $test_id){
            return $tests[$key+1];
        }
    }
    return null;
}

function getCategory($id){
    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM test_categories WHERE id = $id");
    $test = $result->fetch();
    return $test;
}
function getAllTests(){
    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM tests");
    $tests = $result->fetchAll();
    return $tests;
}

function getCriteriaForUser($id){
    $pdo = getPDO();
    //Получить значения критериев для конкретного юзера исходя и с того, что все необходимые тесты должны быть пройдены
    $result = $pdo->query("SELECT DISTINCT criteria_id,criteria.name  FROM criteria_test 
                            JOIN criteria ON criteria.id = criteria_test.criteria_id
                            WHERE test_id IN 
                                  (SELECT test_id FROM user_test_results WHERE user_id = $id)");
    $criteria = $result->fetchAll(PDO::FETCH_ASSOC);

    $total_sum= 0;

    foreach($criteria as $criteria_key => $criterion) {
        $criteria_id = $criterion['criteria_id'];
        $result = $pdo->query("SELECT * FROM criteria_test WHERE criteria_id = $criteria_id");
        $tests_required = $result->fetchAll(PDO::FETCH_ASSOC);
        $tests_required_ids = array_column($tests_required, 'test_id');
        $tests_required = array_combine($tests_required_ids, $tests_required);

        $result = $pdo->query(
            "SELECT avg(result) as avg_result, test_id, complexity  FROM user_test_results WHERE test_id IN 
                                      (SELECT test_id FROM criteria_test WHERE criteria_id = $criteria_id)
                                      GROUP BY test_id, complexity "
        );

        $user_tests = $result->fetchAll(PDO::FETCH_ASSOC);

        $criteria_sum = [];
        foreach ($user_tests as $user_test) {
            if (isset($criteria_sum[$user_test['test_id']])) {
                $criteria_sum[$user_test['test_id']]['val'] += $user_test['avg_result'];
                $criteria_sum[$user_test['test_id']]['count'] += 1;
            } else {
                $criteria_sum[$user_test['test_id']]['val'] = $user_test['avg_result'];
                $criteria_sum[$user_test['test_id']]['count'] = 1;
            }
        }
        foreach ($criteria_sum as $key => $sum) {
            $criteria_sum[$key] = $sum['val'] / $sum['count'];
        }

        $check = true;
        $sum = 0;
        foreach ($tests_required as $test_required) {
            if (!isset($criteria_sum[$test_required['test_id']]) || $test_required['val'] > $criteria_sum[$test_required['test_id']]) {
                $check = false;
                break;
            }
            $sum += $criteria_sum[$test_required['test_id']];
        }
        if ($check) {
            $sum /= count($criteria_sum);
            $criteria[$criteria_key]['sum'] = $sum;
//            $criteria[$criteria_key]['display_sum'] = $sum
            $total_sum+=$sum;
        } else {
            unset($criteria[$criteria_key]);
        }

    }

    foreach ($criteria as $key => $criterion){
        $criteria[$key]['display_sum'] = round($criterion['sum']/$total_sum*100, 2);
    }
    return $criteria;
}

function getPvkForUserByCriteria($id, $criteria){
    $pdo = getPDO();
    if(count($criteria) == 0){
        return [];
    }
    $criteria_ids = array_column($criteria, 'criteria_id');
    $criteria = array_combine($criteria_ids, $criteria);
    $criteria_ids = implode(', ',$criteria_ids);
    $result = $pdo->query("SELECT DISTINCT pvk_id, pvk.name  FROM pvk_criteria 
                        JOIN pvk ON pvk.id = pvk_criteria.pvk_id
                       WHERE criteria_id IN ($criteria_ids)");
    $pvks = $result->fetchAll(PDO::FETCH_ASSOC);
    foreach ($pvks as $pvk_key => $pvk){
        $result = $pdo->query("SELECT criteria_id, val FROM pvk_criteria WHERE pvk_id = {$pvk['pvk_id']}");
        $criteria_for_pvk = $result->fetchAll(PDO::FETCH_ASSOC);
        $sum = 0;
        $count = 0;
        $check = true;
        foreach ($criteria_for_pvk as $criterion_required){
            if(isset($criteria[$criterion_required['criteria_id']])){
                $sum += $criteria[$criterion_required['criteria_id']]['sum']*$criterion_required['val']/100;
                $count++;
            } else {
                $check = false;
                break;
            }

        }
        if($check){
            $pvks[$pvk_key]['result'] = round($sum/$count, 2);
        } else {
            unset($pvks[$pvk_key]);
        }

    }
    return $pvks;
}

function getAVGForTestsArray($tests){
    $sum = [];
    $count = [];
    if(count($tests) == 0)
        return 0;
    foreach ($tests as $test){
        if(isset($sum[$test['complexity']])) {
            $sum[$test['complexity']] += $test['result'];
            $count[$test['complexity']] += 1;
        } else {
            $sum[$test['complexity']] = $test['result'];
            $count[$test['complexity']] = 1;
        }
    }

    foreach ($sum as  $key => $item) {
        $sum[$key] /= $count[$key];

    }

    $min = min($sum);
    $max = max($sum);
    $diff = $max - $min;
    $res = array_map(function($v) use ($min, $diff, $max, $sum) {
        if($diff != 0)
            return (($v - $min)*$min) / $diff+$min;
        else
            return $min/count($sum);
        }, $sum);
    return array_sum($res) / count($res);
}

function getSuitableUsers($professionId){
    $pdo = getPDO();
    $professionsWithPvk = getProfessionsWithPvk();
    $result = $pdo->query("SELECT * FROM users");
    $users = $result->fetchAll(PDO::FETCH_ASSOC);
    foreach ($users as $key => $user){
        $criteria = getCriteriaForUser($user['id']);
        $pvks = getPvkForUserByCriteria($user['id'], $criteria);
        $professions = selectProffessionsByPVK($professionsWithPvk, $pvks);
        $suitable_rate = 0;
        foreach ($professions as $profession_key => $profession){
            if($profession['suitable_rate'] == 0 || $profession['id'] != $professionId){
                unset($professions[$profession_key]);
            } else {
                $suitable_rate = $profession['suitable_rate'];
            }
        }

        $users[$key]['suitable_rate'] = $suitable_rate;
    }

    return $users;
}