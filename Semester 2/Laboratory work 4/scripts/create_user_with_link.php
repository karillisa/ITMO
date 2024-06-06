<?php
require_once "db.php";
$pdo = getPDO();
var_dump($_POST);
if(!(isset($_POST['name']) &&
    isset($_POST['second_name'])
)){
    die("error");
}

$name = $_POST['name'];
$second_name = $_POST['second_name'];


$link = md5(time());
$result = $pdo->query("INSERT INTO users (id, name, second_name, email, password, status, invitation_link)".
    "VALUES (null, '$name', '$second_name', '$link', '', 1, '$link')");
$id = $pdo->lastInsertId();
if($result){
    echo "ok";
    header("Location: ../link_created.php?id=$id");
}