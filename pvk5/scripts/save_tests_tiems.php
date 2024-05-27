<?php
require_once "./gates.php";
check_auth_admin();

require_once "./db.php";
require_once "./get_user_tests.php";

$sql = "";
foreach ($_POST['time'] as $key => $time){
    $sql .= "UPDATE tests SET time = $time WHERE id = $key; ";
}
$pdo = getPDO();
$pdo->query($sql);

header("location: ../edit_tests_time.php");