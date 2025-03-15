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
$result = $pdo->query("SELECT * FROM professions WHERE id = $id LIMIT 1");
$profession = $result->fetch(PDO::FETCH_ASSOC);

$result = $pdo->query("SELECT * FROM pvk");
$pvk_list = $result->fetchAll();

$user = getCurrentUser();
$result = $pdo->query("SELECT user_profession_pvk.id AS user_profession_pvk_id, pvk.name, user_profession_pvk.val, pvk.id  FROM user_profession_pvk INNER JOIN pvk ON user_profession_pvk.pvk_id=pvk.id WHERE profession_id = '{$profession['id']}' AND user_id = '{$user['id']}'");
$user_profession_pvk_list = $result->fetchAll(PDO::FETCH_ASSOC);
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

    <?= include("scripts/menu.php") ?>

  <!-- Content Wrapper -->
  <div id="content-wrapper" class="d-flex flex-column">

    <!-- Main Content -->
    <div id="content">

        <?= include("scripts/menu_top.php") ?>


      <form action="/scripts/add_user_profession_pvk.php" method="post">
          <input type="hidden" name="profession_id" value="<?= $profession['id'] ?>">
      <!-- Begin Page Content -->
      <div id="pvk_wrapper" class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Экспертиза</h1>
        </div>
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h2 class="h3 mb-0 text-gray-800">профессия: <?= $profession['name'] ?></h2>
        </div>

        <!-- Content Row -->
        <div class="row">
          <select class="form-select" aria-label="Выберите ПВК" id="select_pvk" style="max-width: 427px;">
              <?php foreach($pvk_list as $pvk): ?>
            <option value="<?= $pvk['id'] ?>"><?= $pvk['name'] ?></option>
                <?php endforeach; ?>
          </select>
          <div class="col-auto">
            <button id="add_pvk" type="button" class="btn btn-primary">Добавить</button>
          </div>
        </div>
        <?php foreach($user_profession_pvk_list as $user_profession_pvk): ?>
        <div class="row mb-6 mt-5 pvk_item">
          <div class="col-auto mr-3 mt-1" style="min-width: 300px">
            <h5 class="m-0 font-weight-bold text-primary pvk_name"><?= $user_profession_pvk['name'] ?></h5>
          </div>
          <input type="number" name="value[]" placeholder="0%" value="<?= $user_profession_pvk['val'] ?>" max="100" min="0">
          <input class="pvk_id" type="hidden" name="pvk_id[]" value="<?= $user_profession_pvk['id'] ?>">
          <div class="col-auto mr-3 mt-1">
            <h5 class="m-0 font-weight-bold text-primary">%</h5>
          </div>
          <a href="/scripts/remove_user_profession_pvk.php?id=<?= $user_profession_pvk['user_profession_pvk_id'] ?>" class="btn btn-danger btn-icon-split">
            <span class="icon text-white-50">
              <i class="fas fa-trash"></i>
            </span>
            <span class="text remove_pvk">Удалить</span>

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

<div class="row mb-6 mt-5 pvk_item" id="pvk_template" style="display: none">
  <div class="col-auto mr-3 mt-1" style="min-width: 300px">
    <h5 class="m-0 font-weight-bold text-primary pvk_name" style="max-width: 500px;">Упорство</h5>
  </div>
    <div class="col-auto mr-3 mt-1" style="min-width: 300px">
        <div class="row">
          <input type="number" name="value[]" placeholder="0%" value="0" max="100" min="0">
          <input class="pvk_id" type="hidden" name="pvk_id[]" value="0">
          <div class="col-auto mr-3 mt-1">
            <h5 class="m-0 font-weight-bold text-primary">%</h5>
          </div>
          <a href="#" class="btn btn-danger btn-icon-split">
                    <span class="icon text-white-50">
                      <i class="fas fa-trash"></i>
                    </span>
            <span class="text remove_pvk" onclick="remove_pvk(this)">Удалить</span>
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
  $('#add_pvk').click(function(){
    let tmp = $("#pvk_template").clone();
    tmp.show();
    tmp.removeAttr("id");
    tmp.find(".pvk_name").text($("#select_pvk option:selected" ).text());
    tmp.find(".pvk_id").val($("#select_pvk option:selected" ).val());
    $("#pvk_wrapper").append(tmp);
  });
/*  function remove_pvk(e, id){

    $(e).closest(".pvk_item").remove();

  }*/
</script>

<script src="js/notify.min.js"></script>
<script src="js/notifier.js"></script>

</body>

</html>