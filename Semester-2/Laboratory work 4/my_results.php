<?php
require_once "scripts/gates.php";
check_auth();

require_once "scripts/get_user_tests.php";
require_once "scripts/get_professions_with_pvk.php";
$tests = [];
$user = [];

$user = getCurrentUser();
$criteria = getCriteriaForUser($user['id']);
$pvks = getPvkForUserByCriteria($user['id'], $criteria);
$professionsWithPvk = getProfessionsWithPvk();

$professions = selectProffessionsByPVK($professionsWithPvk, $pvks);

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

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Критерии: </h1>
                </div>

                <?php foreach ($criteria as $criterion) { ?>
                    <div class="row">
                        <div class="card shadow mb-3">
                            <!-- Card Header - Accordion -->
                            <a href="#collapseCardExample<?= $criterion['criteria_id'] ?>"
                               class="d-block card-header py-3"
                               data-toggle="collapse"
                               role="button" aria-expanded="true" aria-controls="collapseCardExample">
                                <h3 class="m-6 font-weight-bold text-primary"><?= $criterion['name'] ?></h3>
                            </a>
                            <!-- Card Content - Collapse -->
                            <div class="collapse show" id="collapseCardExample<?= $criterion['criteria_id'] ?>">
                                <div class="card-body">
                                    <h5 class="font-weight-bold mt-2">Значение: <?= $criterion['display_sum'] ?></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                <?php } ?>


                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Профессионально-важные качества: </h1>
                </div>

                <?php foreach ($pvks as $pvk) { ?>
                    <div class="row">
                        <div class="card shadow mb-3">
                            <!-- Card Header - Accordion -->
                            <a href="#collapseCardExample1<?= $pvk['pvk_id'] ?>" class="d-block card-header py-3"
                               data-toggle="collapse"
                               role="button" aria-expanded="true" aria-controls="collapseCardExample">
                                <h3 class="m-6 font-weight-bold text-primary"><?= $pvk['name'] ?></h3>
                            </a>
                            <!-- Card Content - Collapse -->
                            <div class="collapse show" id="collapseCardExample1<?= $pvk['pvk_id'] ?>">
                                <div class="card-body">
                                    <h5 class="font-weight-bold mt-2">Значение: <?= $pvk['result'] ?></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                <?php } ?>


                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Подходящие профессии: </h1>
                </div>
                <?php foreach ($professions as $profession) {
                    if($profession['suitable_rate'] > 0){
                    ?>
                    <div class="row">
                        <div class="card shadow mb-3">
                            <!-- Card Header - Accordion -->
                            <a href="#collapseCardExample2<?= $profession['id'] ?>" class="d-block card-header py-3"
                               data-toggle="collapse"
                               role="button" aria-expanded="true" aria-controls="collapseCardExample">
                                <h3 class="m-6 font-weight-bold text-primary"><?= $profession['name'] ?></h3>
                            </a>
                            <!-- Card Content - Collapse -->
                            <div class="collapse show" id="collapseCardExample2<?= $profession['id'] ?>">
                                <div class="card-body">
                                    <h5 class="font-weight-bold mt-2">Насколько профессия подходит: <?= $profession['suitable_rate'] ?></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                <?php }} ?>
            </div>
            <!-- /.container-fluid -->

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

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>
<script src="js/notify.min.js"></script>
<script src="js/notifier.js"></script>

</body>

</html>

