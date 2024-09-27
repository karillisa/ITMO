<?php
require "db.php";

function getCurrentUser(){
    $pdo = getPDO();
    $user = null;
    if(isset($_COOKIE['auth_email'])) {
        $email = $_COOKIE['auth_email'];
        $result = $pdo->query("SELECT * FROM users WHERE email = '$email'");
        $user = $result->fetch(PDO::FETCH_ASSOC);
    }
    return $user;
}
function getUser($id){
    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM users WHERE id = '$id'");
    $user = $result->fetch(PDO::FETCH_ASSOC);

    return $user;
}

function authUserByLink($link){
    $pdo = getPDO();
    $result = $pdo->query("SELECT * FROM users WHERE invitation_link = '$link'");
    $check = $result->fetch(PDO::FETCH_NUM);
    if($check > 0) {
        setcookie("auth_email", $link, time()+3600*12, '/', $_SERVER['SERVER_NAME']);
        header("Location: ".$_SERVER['REQUEST_URI']);
    }
}


function getStatus(){
    $user = getCurrentUser();
        if ($user)
            return $user['status'];
    return false;
}

function is_admin(){
    return getStatus() == 3;
}

function is_expert(){
    return getStatus() == 2;
}

function is_user(){
    return getStatus() == 1;
}

function check_auth(){
    if(!getStatus()){
        header("Location: ../login.html");
        exit();
    }
}
function check_auth_admin(){
    $status = getStatus();
    if($status != 3){
        header("Location: ".$_SERVER['HTTP_REFERER']);
        exit();
    }
}

function check_auth_expert(){
    $status = getStatus();
    if(!($status == 2 || $status == 3)){
        header("Location: ".$_SERVER['HTTP_REFERER']);
        exit();
    }
}