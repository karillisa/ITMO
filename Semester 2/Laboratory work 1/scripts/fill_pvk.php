<?php
include "db.php";
check_auth_admin();
$pdo = getPDO();


$myfile = fopen("../pvk_list.php", "r") or die("Unable to open file!");
// Output one line until end-of-file
while(!feof($myfile)) {
    $name = trim(fgets($myfile));
    if(strlen($name) > 1)
    $result = $pdo->query("INSERT INTO pvk (id, name)".
        "VALUES (null, '$name')");
}
fclose($myfile);
