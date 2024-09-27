<?php
require_once "gates.php";
require_once "db.php";


function getTestResults($test_id){
    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM user_test_results, users WHERE user_test_results.test_id = $test_id AND user_test_results.user_id = users.id  ORDER BY users.age");
    $tests = $result->fetchAll(PDO::FETCH_ASSOC);
    return $tests;
}

function getTestResultsByAge($test_id, $age){
    $pdo = getPDO();
    $max_age = $age+5;
    $min_age = $age-5;
    $result = $pdo->query("SELECT * FROM user_test_results, users WHERE user_test_results.test_id = $test_id AND ".
        "user_test_results.user_id = users.id AND users.age >= $min_age AND users.age <= $max_age ORDER BY users.age");
    $tests = $result->fetchAll(PDO::FETCH_ASSOC);
    return $tests;
}


