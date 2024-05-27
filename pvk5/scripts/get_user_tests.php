<?php
require_once "gates.php";
require_once "db.php";


function getTestsForUser($id, $category_id){
    $pdo = getPDO();
    if($category_id == 4) {
        $sql = "SELECT * FROM user_tests, tests WHERE 
                                    user_tests.user_id = $id AND 
                                    user_tests.test_id = tests.id  AND
                                    tests.category_id = $category_id
                                    ORDER BY user_tests.order_for_user";
    } else {
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
