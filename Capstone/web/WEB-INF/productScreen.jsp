<%-- 
    Document   : productScreen
    Description: ONLY FOR MANAGER USER
    Created on : Mar 23, 2021, 4:17:28 PM
    Author     : Nhu Phan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <%@include file="includes/externalCSSLinks.html"%>
        <title>Bridgeland Bread - Products</title>
        <%@include file="javascript/jsProductScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>        
        <%-- page header --%>     
        <%@include file="includes/header.html"%>

        <div class="container-fluid">
            <div class="row">
                <%-- manager side navigation menu --%>
                <jsp:include page="includes/managerSideNavigationMenu.jsp" />

                <%-- Product Section --%>
                <div class="col-lg-10">
                    <div class="row mt-4 ml-1">
                        <p class="h4 font-weight-light">Manage Products</p>
                    </div>

                    <!-- Breadcrumbs nav -->
                    <div class="row">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb" style="background-color: white;">
                                <li class="breadcrumb-item active">Breads</li>
                                <li class="breadcrumb-item"><a href="#subscriptionListing">Subscription</a></li>
                            </ol>
                        </nav>
                    </div>

                    <!-- sorting and adding new product option -->
                    <div class="row" id="breadListing">
                        <div class="col-lg-8">
                            <label for="sortbyOptions">Sort by:</label>
                            <select class="selectpicker" 
                                    name="sortbyOptions" 
                                    id="sortbyOptions"
                                    onchange="getSortedProduct()"
                                    >
                                <option>Product Name</option>
                                <option>Price</option>
                            </select>
                            <select class="selectpicker pl-2" 
                                    name="ascDesc" 
                                    id="ascDesc"
                                    onchange="getSortedProduct()"
                                    >
                                <option>Ascending</option>
                                <option>Descending</option>
                            </select>
                        </div>
                        <div class="col-lg-4">
                            <button type="button" class="btn btn-outline-dark pr-4 pl-4 mr-2" data-toggle="modal" data-target="#addProductModal">
                                Add New Product
                            </button>
                        </div>
                    </div>

                    <!-- Bread product listing -->
                    <div class="row mt-4">
                        <div class="card-deck">
                            <!-- 
                                Dynamically print bread product listing
                            -->
                            <c:forEach var='product' items='${requestScope.breadProductList}' varStatus='i'>
                                <div class="card">
                                    <img class="card-img-top" src="${pageContext.request.contextPath}/<c:out value='${product.productImage}'/>" alt="product image">
                                    <div class="card-body">
                                        <h5 class="card-title">${product.productName}</h5>
                                        <p class="card-text">$${product.productPrice}</p>
                                    </div>
                                    <div class="card-footer">
                                        <button type="button" 
                                                class="btn btn-outline-dark"
                                                onclick="editProduct('<c:out value='${product.productId}'/>')"
                                                >
                                            Edit
                                        </button>
                                        <button type="button" 
                                                class="btn btn-outline-danger"
                                                onclick="deleteProduct('<c:out value='${product.productId}'/>')"
                                                >
                                            Delete
                                        </button>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- Subscription Listing -->
                    <div class="row mt-4" id="subscriptionListing">
                        <div class="col">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb" style="background-color: white;">
                                    <li class="breadcrumb-item"><a href="#breadListing">Breads</a></li>
                                    <li class="breadcrumb-item active">Subscription</li>
                                </ol>
                            </nav>
                        </div>
                        <div class="col">
                            <button type="button" class="btn btn-outline-dark pr-4 pl-4 mr-2" data-toggle="modal" data-target="#addSubscriptionModal">
                                Add New Subscription
                            </button>
                        </div>
                    </div>

                    <!-- Table of subscriptions -->
                    <div class="row">
                        <div class="col pt-4 mr-5">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Subscription Description</th>
                                        <th scope="col">Price</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- 
                                        Dynamically print subscription product listing
                                    -->
                                    <c:forEach var='product' items='${requestScope.subscriptionProductList}' varStatus='i'>
                                        <tr>
                                            <th scope="row">${product.productId}</th>
                                            <th>${product.productDescription}</th>
                                            <td>$${product.productPrice}</td>
                                            <td>
                                                <button type="button" 
                                                        class="btn btn-outline-dark"
                                                        onclick="editProduct('<c:out value='${product.productId}'/>')"
                                                        >
                                                    Edit
                                                </button>
                                                <button type="button" 
                                                        class="btn btn-outline-danger"
                                                        onclick="deleteProduct('<c:out value='${product.productId}'/>')"
                                                        >
                                                    Delete
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!-- Add New Product Modal -->
                    <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog"
                         aria-labelledby="addProductModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="addProductModalLabel">Add New Product</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- Create new product form -->
                                    <form action="productServices" method="POST" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="newProductName" class="col-form-label">Product Name</label>
                                            <input type="text" class="form-control" name="newProductName"
                                                   id="newProductName" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="newProductImage">Upload image</label>
                                            <input type="file" class="form-control-file" name="newProductImage" id="newProductImage">
                                        </div>
                                        <div class="form-group">
                                            <label for="newProductPrice" class="col-form-label">Price</label>
                                            <input type="number" class="form-control" name="newProductPrice"
                                                   id="newProductPrice" min="0" step=".01" required></input>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Add New Product</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Add New Subscription Modal -->
                    <div class="modal fade" id="addSubscriptionModal" tabindex="-1" role="dialog"
                         aria-labelledby="addSubscriptionModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="addSubscriptionModalLabel">Add New Subscription</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- Create new subscription form -->
                                    <form action="productServices" method="POST">
                                        <div class="form-group">
                                            <label for="subscriptionDescription" class="col-form-label">Description</label>
                                            <input type="text" class="form-control" name="subscriptionDescription"
                                                   id="subscriptionDescription">
                                        </div>
                                        <div class="form-group">
                                            <label for="newSubscriptionPrice" class="col-form-label">Price</label>
                                            <input type="number" class=" form-control" name="newSubscriptionPrice"
                                                   id="newSubscriptionPrice" min="0" step=".01" required></input>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Add New Subscription</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- END Product Section --%>
            </div>
            <%@include file="includes/externalJSLibrary.html"%>
    </body>
</html>
