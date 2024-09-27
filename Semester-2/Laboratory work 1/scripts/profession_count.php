<?php
require_once "gates.php";
check_auth_expert();
require_once "db.php";
$pdo = getPDO();


$result = $pdo->query("SELECT COUNT(*) as count FROM professions");
$result = $result->fetch();
print($result['count']);

