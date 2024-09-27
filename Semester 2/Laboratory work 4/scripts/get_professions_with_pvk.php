<?php
require_once __DIR__."/gates.php";
check_auth();
require_once __DIR__."/db.php";


function getUser_profession_pvkByProffesionId($array, $profession_id){
    return array_filter($array, function($item) use ($profession_id){
        return $item['profession_id'] === $profession_id;
    });
}
function getUser_profession_pvkByPvk($array, $pvk_id){
    return array_filter($array, function($item) use ($pvk_id){
        return $item['pvk_id'] === $pvk_id;
    });
}

function getProfessionsWithPvk()
{

    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM professions WHERE hidden = false");
    $professions = $result->fetchAll();
    $result = $pdo->query("SELECT * FROM user_profession_pvk INNER JOIN pvk ON user_profession_pvk.pvk_id=pvk.id ORDER BY profession_id ASC");
    $user_profession_pvk_list = $result->fetchAll();
    $result = $pdo->query("SELECT COUNT(DISTINCT user_id, profession_id) AS 'count', user_id FROM user_profession_pvk GROUP BY user_id");
    $result = $result->fetchAll();
    $users_authority = [];
    $max = 0;
    foreach ($result as $key => $item) {
        $users_authority[$item['user_id']] = $item['count'];
        if ($item['count'] > $max)
            $max = $item['count'];
    }
    foreach ($users_authority as $key => $item) {
        $users_authority[$key] = $item / $max;
    }

    foreach ($professions as $key => $profession) {
        $user_profession_pvk_s = getUser_profession_pvkByProffesionId($user_profession_pvk_list, $profession['id']);
        $numberOfExpertize = array();
        foreach ($user_profession_pvk_s as $i => $user_profession_pvk) {
            $professions[$key]['pvk'][$user_profession_pvk['pvk_id']]['id'] = $user_profession_pvk['pvk_id'];
            $professions[$key]['pvk'][$user_profession_pvk['pvk_id']]['name'] = $user_profession_pvk['name'];
            if (isset($professions[$key]['pvk'][$user_profession_pvk['pvk_id']]['sum'])) {
                $professions[$key]['pvk'][$user_profession_pvk['pvk_id']]['sum'] += $user_profession_pvk['val'];
                $professions[$key]['pvk'][$user_profession_pvk['pvk_id']]['count'] += 1;
                $numberOfExpertize[$user_profession_pvk['pvk_id']] += 1 * $users_authority[$user_profession_pvk['user_id']];
            } else {
                $professions[$key]['pvk'][$user_profession_pvk['pvk_id']]['sum'] = $user_profession_pvk['val'] * $users_authority[$user_profession_pvk['user_id']];
                $professions[$key]['pvk'][$user_profession_pvk['pvk_id']]['count'] = 1;
                $numberOfExpertize[$user_profession_pvk['pvk_id']] = 1 * $users_authority[$user_profession_pvk['user_id']];
            }
        }
        if(!empty($numberOfExpertize)) {
            $max = max($numberOfExpertize);
        } else
            $professions[$key]['pvk'] = array();

        foreach ($professions[$key]['pvk'] as $i => $pvk) {
            $professions[$key]['pvk'][$i]['sum'] = round($professions[$key]['pvk'][$i]['sum'] / $max, 1);
        }
    }

    return $professions;
}

function selectProffessionsByPVK($professions, $pvks){
    $pvks = array_combine(array_column($pvks, 'pvk_id'), $pvks);
    foreach ($professions as $prof_key => $profession){
        $professions[$prof_key]['suitable_rate'] = 0;
        $professions[$prof_key]['count'] = 0;
        $check = true;
        foreach ($profession['pvk'] as $id => $profession_pvk){
            if(isset($pvks[$id])){
                $professions[$prof_key]['suitable_rate'] += $pvks[$id]['result']*$profession_pvk['sum']/100;
                $professions[$prof_key]['count'] += 1;
            } else {
                $check = false;
                break;
            }
        }

        if($check){
            $professions[$prof_key]['suitable_rate'] /= $professions[$prof_key]['count'];
            $professions[$prof_key]['suitable_rate'] = round($professions[$prof_key]['suitable_rate'], 2);
        } else {
            $professions[$prof_key]['suitable_rate'] = 0;
        }
    }
    return $professions;
}