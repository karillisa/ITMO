<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/index.php">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Сенсомоторные тесты</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="/main.php">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Мои тесты</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Меню
    </div>
    <?php
    if(is_admin() || is_expert()){
        ?>
    <li class="nav-item">
        <a class="nav-link" href="/users.php"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fa-solid fa-list-ul"></i>
            <span>Список пользователей</span>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="/generate_invitation_link.php"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fa-solid fa-list-ul"></i>
            <span>Пригласить респондента</span>
        </a>
    </li>

        <li class="nav-item">
            <a class="nav-link" href="/general_result.php"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fa-solid fa-list-ul"></i>
                <span>Все результаты</span>
            </a>
        </li>
    <?php } ?>


    <!-- Divider -->
    <hr class="sidebar-divider">





</ul>
<!-- End of Sidebar -->