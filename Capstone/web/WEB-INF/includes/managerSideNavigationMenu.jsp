<%-- 
    Document   : managerSideNavigationMenu
    Description: side navigation menu of screens for MANAGER ACCESS ONLY
    Created on : Mar 1, 2021, 1:47:02 PM
    Author     : Nhu Phan
--%>
<div class="col-lg-2">
    <ul class="nav navbar-light bg-light navbar-expand-lg" style="height: 100%;">
        <div class="collapse navbar-collapse flex-column" id="sidemenu">
            <li class="nav-item d-flex flex-row mt-lg-4">
                <span class="material-icons md-dark p-2">
                    assignment
                </span>
                <a class="nav-link text-dark" 
                   href="Controller?goToDailyReport=true&loginUserEmail=${sessionScope.email}"
                >
                    Report
                </a>
            </li>
            <li class="nav-item d-flex flex-row">
                <span class="material-icons md-dark p-2">
                    local_shipping
                </span>
                <a class="nav-link text-dark" 
                    href="Controller?goToDeliverySchedule=true&loginUserEmail=${sessionScope.email}"
                >
                    Delivery Schedule
                </a>
            </li>
            <li class="nav-item d-flex flex-row">
                <span class="material-icons md-dark p-2">
                    people
                </span>
                <a class="nav-link text-dark" 
                   href="Controller?goToManageClients=true&loginUserEmail=${sessionScope.email}"
                >
                    Manage Clients
                </a>
            </li>
            <li class="nav-item d-flex flex-row">
                <span class="material-icons md-dark p-2">
                      breakfast_dining
                </span>
                <a class="nav-link text-dark" 
                   href="Controller?goToManageProducts=true&loginUserEmail=${sessionScope.email}"
                >
                    Manage Products
                </a>
            </li>
            <li class="nav-item d-flex flex-row">
                <span class="material-icons md-dark p-2">
                    perm_identity
                </span>
                <a class="nav-link text-dark" 
                   href="LoginServices?logout=true&loginUserEmail=${sessionScope.email}"
                >
                    Log Out
                </a>
            </li>
        </div>
    </ul>
</div>
