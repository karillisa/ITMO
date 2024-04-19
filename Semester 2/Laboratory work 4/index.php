<?php
require_once "scripts/gates.php";

?>
<html>
<head>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Arima+Madurai:100,200,300,400,500,700,800,900" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="css/style.css" rel="stylesheet" id="bootstrap-css">

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>


</head>
<body>
<div class="area" >
    <ul class="circles">
        <li></li>

    </ul>
</div >
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand pt-4" href="/">Профессии и ПВК</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <?php if(getStatus()){ ?>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="main.php">В приложение</a>
                </li>
                <?php } else { ?>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="login.html">Вход</a>
                </li>
                <?php } ?>
            </ul>
        </div>

    </div>
</nav>





<script>
    $('.close-popup').click(function (e){
        $(e.target).closest('.popup-shadow').hide();
    });
    $('.popup-shadow').click(function (e){
        $(e.target).hide();
    });

    $('.expert_opinion').click(function (e){
        var id = $(e.target).data('profession');
        console.log(id);
        console.log($('#popup_'+id));
        $('#popup_'+id).show();
    });


    $().ready(function(){
        $('[rel="tooltip"]').tooltip();

    });

    function rotateCard(btn){
        var $card = $(btn).closest('.card-container');
        console.log($card);
        if($card.hasClass('hover')){
            $card.removeClass('hover');
        } else {
            $card.addClass('hover');
        }
    }




</script>
<script src="js/notify.min.js"></script>
<script src="js/notifier.js"></script>



</body>
</html>