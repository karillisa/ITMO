<?php
setcookie("auth_email", '', time()-10, '/', $_SERVER['SERVER_NAME']);
header("Location: ../index.php");