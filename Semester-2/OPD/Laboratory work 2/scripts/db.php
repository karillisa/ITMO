<?php
function getPDO()
{
    $pdo = new PDO("mysql:host=localhost;dbname=pvk2", 'root', '');
    $pdo->setAttribute(PDO::MYSQL_ATTR_USE_BUFFERED_QUERY, false);
    return $pdo;
}
function getSalt(){
    return "n&fq)5@&*lb4^0bw4s-e";
}