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
        <a class="nav-link" href="/main.php?test_category=1">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Тесты на внимание</span></a>
    </li>
    <li class="nav-item active">
        <a class="nav-link" href="/main.php?test_category=2">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Тесты на память</span></a>
    </li>
    <li class="nav-item active">
        <a class="nav-link" href="/main.php?test_category=3">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Тесты на мышление</span></a>
    </li>
    <li class="nav-item active">
        <a class="nav-link" href="/main.php?test_category=4">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Другие тесты</span></a>
    </li>
    <li class="nav-item active">
        <a class="nav-link" href="/my_results.php">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Мои результаты</span></a>
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
     <?php
    if(is_admin()){
    ?>
        <li class="nav-item">
            <a class="nav-link" href="/edit_tests_time.php"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fa-solid fa-list-ul"></i>
                <span>Изменить время тестов</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/professions.php"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fa-solid fa-list-ul"></i>
                <span>Список профессий</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/professions_with_pvk.php"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fa-solid fa-list-ul"></i>
                <span>Список профессий с ПВК</span>
            </a>
        </li>

        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link" href="/pvk.php"
               aria-expanded="true" aria-controls="collapseUtilities">
                <i class="fa-solid fa-list-ul"></i>
                <span>Список ПВК</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/criteria.php"
               aria-expanded="true" aria-controls="collapseUtilities">
                <i class="fa-solid fa-list-ul"></i>
                <span>Критерии</span>
            </a>
        </li>
    <?php
    }
    ?>
    <!-- Divider -->
    <hr class="sidebar-divider">
</ul>
<!-- End of Sidebar -->