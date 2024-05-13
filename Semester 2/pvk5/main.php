<?php
require_once "scripts/gates.php";
check_auth();

require_once "scripts/get_user_tests.php";
$category_id = 1;
if(isset($_GET['test_category']))
    $category_id = intval($_GET['test_category']);
$tests = getTestsForCurrentUser($category_id);
//print("<pre>");
//print_r($tests);
//print("</pre>");
$category = getCategory($category_id);
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

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Мои тесты (<?= $category['name'] ?>)</h1>
                    </div>
                    <a href="tests/test.php?category=<?= ($category['id'] ?? "") ?>&id=<?= (isset($tests[0]['id'])?$tests[0]['id']:"") ?>" class="btn btn-success btn-icon-split mb-5">
                                                    <span class="icon text-white-50">
                                                        <i class="fas fa-pen"></i>
                                                    </span>
                        <span class="text">Пройти тестирование</span>
                    </a>

                    <!-- Content Row -->
                    <div class="row">
                        <!-- Collapsable Card Example -->
                        <div class="col-lg-10 col-md-10 col-sm-12">

                            <?php foreach ($tests as $test): ?>
                            <div class="card shadow mb-3">
                            <!-- Card Header - Accordion -->
                            <a href="#collapseCardExample<?= $test['id'] ?>" class="d-block card-header py-3" data-toggle="collapse"
                               role="button" aria-expanded="true" aria-controls="collapseCardExample">
                                <h3 class="m-6 font-weight-bold text-primary"><?= $test['title'] ?></h3>
                            </a>
                            <!-- Card Content - Collapse -->
                            <div class="collapse show" id="collapseCardExample<?= $test['id'] ?>">
                                <div class="card-body">
                                    <h5 class="font-weight-bold mt-2">Результаты тестирования:</h5>

                                    <?php
                                    if(sizeof($test['results'])){
                                    $first_res = 0;
                                    $last_res = 0;
                                    $max = max(array_column($test['results'], 'result'));
                                    if(isset($test['results'][0])){
                                        $first_res = $test['results'][0]['result'];
                                        $last_res = $test['results'][sizeof($test['results'])-1]['result'];
                                    }
                                    $dynamic = $last_res-$first_res;
                                    $dynamic_percent = 0;
                                    if($max>0)
                                        $dynamic_percent = round($last_res/$max*100 - $first_res/$max*100, 2);
                                    foreach($test['results'] as $result):


                                        ?>
                                    <h4 class="small font-weight-bold mt-3"><?= $result['date'] ?> (сложность: <?= $result['complexity'] ?>)<span
                                            class="float-right"><?= $result['result'] ?> <?= $test['measure'] ?></span></h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar bg-success" role="progressbar" style="width: <?= ($max > 0)?$result['result']/$max*100:0 ?>%"
                                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <?php endforeach; ?>

                                    <h5 class="font-weight-bold mt-4">Динамика: <?= (($dynamic>=0)?"+":"-").abs($dynamic) ?> <?= $test['measure'] ?> (<?= (($dynamic>=0)?"ухудшилась":"улучшилась") ?> на <?= abs($dynamic_percent) ?>%)</h5>

                                    <?php } ?>

                                </div>
                            </div>
                        </div>
                            <?php  endforeach; ?>

                        </div>
                    </div>


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