<?php
require_once "scripts/gates.php";
check_auth();

require_once "scripts/db.php";
require_once "scripts/get_test_results.php";
$pdo = getPDO();
$result = $pdo->query("SELECT * FROM tests");
$tests = $result->fetchAll();

$sex = [
    0 => "Женский",
    1 => "Мужской",
];

$test_id = 1;
if (isset($_GET['test_id'])) {
    $test_id = $_GET['test_id'];
}
$age = 0;
if (isset($_GET['age'])) {
    $age = $_GET['age'];
}
$tests_results = [];
if($age)
    $tests_results = getTestResultsByAge($test_id, $age);
else
    $tests_results = getTestResults($test_id);
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

    <div id="content-wrapper" class="d-flex flex-column">

        <div id="content">

            <?php include("scripts/menu_top.php") ?>

            <div class="container-fluid">

                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Итоговые результаты</h1>
                </div>

                <div class="row">
                    <div class="card shadow mb-8 mt-3">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Итоговые результаты</h6>
                            <h6 class="m-0 font-weight-bold text-warning">Выберите тест</h6>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="btn-group-vertical">
                                        <?php foreach ($tests as $test): ?>
                                            <a href="general_result.php?test_id=<?= $test['id'] ?>" type="button"
                                               class="btn <?= ($test['id'] == $test_id) ? "btn-primary" : "btn-secondary" ?>">
                                                <?= $test['title'] ?>
                                            </a>
                                        <?php endforeach; ?>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <form action="/general_result.php" method="get">
                                        <input name="test_id" type="hidden" value="<?= $test_id ?>">
                                        <label for="customRange1" class="form-label">Выберите возраст</label>

                                        <input class="form-range" type="range" id="customRange1" name="age" min="10" max="100" value="<?= (!$age)?30:$age ?>" step="5" style="width: 340px;"  oninput="this.nextElementSibling.value = this.value"/>
                                        <output style="width: 30px;"><?= (!$age)?30:$age ?></output>
                                        <br>
                                        <button class="btn btn-primary">Применить</button>
                                        <a href="/general_result.php?test_id=<?= $test_id ?>" class="btn btn-danger">Показать все</a>
                                    </form>
                                </div>
                            </div>
                        </div>


                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0"
                                       style="min-width: 700px">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Пользователь</th>
                                        <th>Пол</th>
                                        <th>Возраст</th>
                                        <th>Результат</th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th>ID</th>
                                        <th>Пользователь</th>
                                        <th>Пол</th>
                                        <th>Возраст</th>
                                        <th>Результат</th>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <?php foreach ($tests_results as $tests_result): ?>
                                        <tr>
                                            <td><?= $tests_result['id'] ?></td>
                                            <td>
                                                <a href="/test_results.php?id=<?= $tests_result['user_id'] ?>"><?= $tests_result['name'] . ' ' . $tests_result['second_name'] ?></a>
                                            </td>
                                            <td><?= $sex[$tests_result['sex']] ?></td>
                                            <td><?= $tests_result['age'] ?></td>
                                            <td><?= $tests_result['result'] ?></td>
                                        </tr>
                                    <?php endforeach; ?>
                                    </tbody>
                                </table>
                            </div>
                        </div>
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
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTable').DataTable({});
    });
</script>

<script src="js/notify.min.js"></script>
<script src="js/notifier.js"></script>
</body>

</html>