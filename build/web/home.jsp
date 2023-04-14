<%-- 
    Document   : home.jsp
    Created on : Apr 13, 2023, 9:25:02 AM
    Author     : Dell
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,quang.dev.data.dao.model.Product"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <% List<Product> productList =(List<Product>)request.getAttribute("productList");%>
        <h2>ProductList</h2>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">price</th>
                    <th scope="col">Description</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Delete</th>
                    <th scope="col">Edit</th>



                </tr>
            </thead>
            <tbody>
                <% for(Product product: productList){ %>
                <tr>
                    <td><%= product.getName() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= product.getDescription() %></td>
                    <td><%= product.getQuantity() %></td>
                    <td>
                        <form action="DeleteServlet" method="POST">
                            <input type="hidden" name="_method" value="DELETE">
                            <input type="hidden" name="product_id" value="<%= product.getId() %>">
                            <!-- add any other form fields -->
                            <input type="submit" value="Delete">
                        </form></td>
                    <td><a href="EditServlet?product_id=<%= product.getId() %>">Edit</a></td>

                </tr>
                <% } %>

            </tbody>
        </table>
    </body>

</html>
