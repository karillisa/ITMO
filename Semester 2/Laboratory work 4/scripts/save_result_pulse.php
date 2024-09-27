<?php
require_once "gates.php";
check_auth();


require_once "get_user_tests.php";
$pdo = getPDO();

$result_id = intval($_GET['id']);
$user_id = intval($_POST['user_id']);
$start_pulse = intval($_POST['start_pulse'])  ?? 0;
$end_pulse = intval($_POST['end_pulse'])  ?? 0;
$during_pulse = $_POST['during_pulse'];

$during_pulse = json_encode($during_pulse)  ?? "";

$sql = "UPDATE user_test_results SET start_pulse = $start_pulse, end_pulse = $end_pulse, during_pulse = '$during_pulse' WHERE id = $result_id";
var_dump($sql);
$result = $pdo->query($sql);


if($result){
    echo "ok";

    header("Location: ../test_results.php?id=$user_id");
}