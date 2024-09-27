<?php
require_once "gates.php";
check_auth_admin();
require_once "db.php";
$pdo = getPDO();

if(!(isset($_POST['name']))
){
    die("error");
}

$name = $_POST['name'];



if(strlen($name) < 4){
    die("error2");
}


$result = $pdo->query("INSERT INTO pvk (id, name)".
"VALUES (null, '$name')");

if($result){
    echo "ok";
    header("Location: ../pvk.php");
}