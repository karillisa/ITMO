<?php
require_once "gates.php";
require_once "db.php";


function getTestsForUser($id){
    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM user_tests, tests WHERE user_tests.user_id = $id AND user_tests.test_id = tests.id  ORDER BY user_tests.order_for_user");
    $tests = $result->fetchAll(PDO::FETCH_ASSOC);
    foreach($tests as $key => $test){
        $result = $pdo->query("SELECT * FROM user_test_results WHERE user_test_results.user_id = $id AND user_test_results.test_id = {$test['id']}");
        $tests[$key]['results'] = $result->fetchAll(PDO::FETCH_ASSOC);
    }
    return $tests;
}
function getTestsForCurrentUser(){
    $id = getCurrentUser()['id'];
    return getTestsForUser($id);
}

function getTestById($id){
    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM tests WHERE id = $id");
    $test = $result->fetch();
    return $test;
}

function getNextTestForCurrentUser($test_id){
    $pdo = getPDO();
    $id = getCurrentUser()['id'];
    $result = $pdo->query("SELECT * FROM user_tests, tests WHERE user_tests.user_id = $id AND user_tests.test_id = tests.id ORDER BY user_tests.order_for_user");
    $tests = $result->fetchAll();
    foreach ($tests as $key => $test) {
        if($test['test_id'] == $test_id){
            return $tests[$key+1];
        }
    }
    return null;
}

