<?php
require_once "scripts/gates.php";

require_once "scripts/get_professions_with_pvk.php";
$professions = getProfessionsWithPvk();
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
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
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
<div class="container" style="padding-top: 100px">
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
            <?php foreach($professions as $profession): ?>
            <div class="col-md-4 col-sm-6">
                <div class="card-container">
                    <div class="card">
                        <div class="front">
                            <div class="cover">
                                <img src="img/img1.jpeg"/>
                            </div>
                            <div class="content">
                                <div class="main">
                                    <h3 class="name"><?= $profession['name'] ?></h3>
                                </div>

                            </div>
                        </div> <!-- end front panel -->
                        <div class="back">
                            <div class="header">
                                <h5 class="motto"><?= $profession['name'] ?></h5>
                            </div>
                            <div class="content">
                                <div class="main">
                                    <p class="text-center"><?= $profession['description'] ?></p>
                                </div>
                            </div>
                            <div class="footer">
                                <a href="/examination.php">Пройти тест</a><br>
                                <a class="expert_opinion" data-profession="<?= $profession['id'] ?>" href="#">Узнать мнение экспертов</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <?php endforeach; ?>
        </div> <!-- end col-sm-10 -->
    </div> <!-- end row -->
    <div class="space-200"></div>
</div>

<?php foreach($professions as $profession): ?>
<div class="popup-shadow" id="popup_<?= $profession['id'] ?>">
    <div class="popup">
        <div class="close-popup">
            [X]
        </div>
        <h1 class="font-weight-bold mt-4"><?= $profession['name'] ?></h1>
        <br>
        <br>
        <h3 class="font-weight-bold mt-4">Профессионально-важные качества:</h3>
        <br>
        <?php foreach($profession['pvk'] as $pvk): ?>
            <h4 class="small font-weight-bold mt-3"><?= $pvk['name'] ?><span
                        class="float-right"><?= $pvk['sum'] ?>%</span></h4>
            <div class="progress mb-4">
                <div class="progress-bar bg-success" role="progressbar" style="width: <?= $pvk['sum'] ?>%"
                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
        <?php endforeach; ?>
    </div>

</div>
<?php endforeach; ?>


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