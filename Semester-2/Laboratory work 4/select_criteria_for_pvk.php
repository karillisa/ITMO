<?php
require_once "scripts/gates.php";
check_auth_expert();
if(!isset($_GET['id'])){
    header('Location: main.php');
    exit();
}
$id = $_GET['id'];
require_once "scripts/db.php";
$pdo = getPDO();
$pdo->setAttribute(PDO::MYSQL_ATTR_USE_BUFFERED_QUERY, true);
$result = $pdo->query("SELECT * FROM pvk WHERE id = $id LIMIT 1");
$pvk = $result->fetch(PDO::FETCH_ASSOC);

$result = $pdo->query("SELECT * FROM criteria");
$criteria_list = $result->fetchAll();

$user = getCurrentUser();
$result = $pdo->query("SELECT *  FROM pvk_criteria INNER JOIN criteria ON pvk_criteria.criteria_id=criteria.id WHERE pvk_id = '$id '");
$pvk_criteria_list = $result->fetchAll(PDO::FETCH_ASSOC);

?>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <?php include("scripts/menu.php") ?>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <?php include("scripts/menu_top.php") ?>


            <form action="/scripts/add_criteria_pvk.php" method="post">
                <input type="hidden" name="pvk_id" value="<?= $pvk['id'] ?>">
                <!-- Begin Page Content -->
                <div id="criteria_wrapper" class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Выбор критериев для ПВК</h1>
                    </div>
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h2 class="h3 mb-0 text-gray-800">ПВК: <?= $pvk['name'] ?></h2>
                    </div>
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h2 class="h4 mb-0 text-primary">*вес указывается в процентах</h2>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        <select class="form-select" aria-label="Выберите ПВК" id="select_criteria" style="max-width: 427px;">
                            <?php foreach($criteria_list as $criteria): ?>
                                <option value="<?= $criteria['id'] ?>"><?= $criteria['name'] ?></option>
                            <?php endforeach; ?>
                        </select>
                        <div class="col-auto">
                            <button id="add_criteria" type="button" class="btn btn-primary">Добавить</button>
                        </div>
                    </div>
                    <?php foreach($pvk_criteria_list as $pvk_criteria): ?>
                        <div class="row mb-6 mt-5 criteria_item">
                            <div class="col-auto mr-3 mt-1" style="min-width: 300px">
                                <h5 class="m-0 font-weight-bold text-primary criteria_name"><?= $pvk_criteria['name'] ?></h5>
                            </div>
                            <div class="col-auto mr-3 mt-1">
                                <h5 class="m-0 font-weight-bold text-primary">вес:</h5>
                            </div>
                            <input type="number" name="value[]" placeholder="0%" value="<?= $pvk_criteria['val'] ?>" max="100" min="0">
                            <input class="criteria_id" type="hidden" name="criteria_id[]" value="<?= $pvk_criteria['id'] ?>">
                            <a href="/scripts/remove_pvk_criteria.php?pvk_id=<?= $pvk_criteria['pvk_id'] ?>&criteria_id=<?= $pvk_criteria['criteria_id'] ?>" class="btn btn-danger btn-icon-split ml-4">
                                <span class="icon text-white-50">
                                  <i class="fas fa-trash"></i>
                                </span>
                                <span class="text remove_criteria">Удалить</span>
                            </a>
                        </div>
                    <?php endforeach; ?>


                </div>
                <button type="submit" class="btn btn-success mb-3 mt-5 ml-3">Сохранить</button>

            </form>

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Студент: ФИО, группа: 1111, 2024 г.</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Вы уверены, что хотите выйти?</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Отмена</button>
                <a class="btn btn-primary" href="/scripts/logout.php">Выйти</a>
            </div>
        </div>
    </div>
</div>

<div class="row mb-6 mt-5 criteria_item" id="criteria_template" style="display: none">
    <div class="col-auto mr-3 mt-1" style="min-width: 300px">
        <h5 class="m-0 font-weight-bold text-primary criteria_name" style="max-width: 500px;">Упорство</h5>
    </div>
    <div class="col-auto mr-3 mt-1" style="min-width: 300px">
        <div class="row">
            <div class="col-auto mr-3 mt-1">
                <h5 class="m-0 font-weight-bold text-primary">вес:</h5>
            </div>
            <input type="number" name="value[]" placeholder="0%" value="0" max="100" min="0">
            <input class="criteria_id" type="hidden" name="criteria_id[]" value="0">
            <a href="#" class="btn btn-danger btn-icon-split ml-4">
                    <span class="icon text-white-50">
                      <i class="fas fa-trash"></i>
                    </span>
                <span class="text remove_criteria" onclick="remove_criteria(this)">Удалить</span>
            </a>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>
<script>
    $('#add_criteria').click(function(){
        let tmp = $("#criteria_template").clone();
        tmp.show();
        tmp.removeAttr("id");
        tmp.find(".criteria_name").text($("#select_criteria option:selected" ).text());
        tmp.find(".criteria_id").val($("#select_criteria option:selected" ).val());
        $("#criteria_wrapper").append(tmp);
    });
    function remove_criteria(e, id){

        $(e).closest(".criteria_item").remove();

    }
</script>

<script src="js/notify.min.js"></script>
<script src="js/notifier.js"></script>

</body>

</html>