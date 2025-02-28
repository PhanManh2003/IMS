<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
    </head>
    <body>
        <div style="text-align: center; margin-bottom: 20px;">
            <form action="ProductControllerTest?action=search" method="POST">
                <input type="text" name="keyword" placeholder="Search for products...">
                <input type="submit" value="Search">
            </form>
        </div>
        
        
        
        <button onclick="insert()">Add</button>
        
        <br><br>
        <table border="1" style="border-collapse: collapse;">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${listProduct}" var="p">
                <tr>
                    <td name="id">${p.id}</td>
                    <td name="name">${p.name}</td>
                    <td name="quantity">${p.quantity}</td>
                    <td name="price">${p.price}</td>
                    <td name="option">
                        <button onclick="update(this)">Edit</button>
                        
                        <form method="POST" action="ProductControllerTest?action=delete"
                              style="display: inline-block">
                            <input type="hidden" name="id" value="${p.id}">
                            <button type="button" onclick="deleteProduct(this)">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <form action="ProductControllerTest?action=insert" id="formAddEdit" style="display: none;" method="POST">
            <h1>Insert</h1>
            <input type ="hidden" name="id" value="0">
            Name: <input type ="text" name="name"><br>
            Quantity: <input type ="number" name="quantity"><br>
            Price: <input type="text" name="price"><br>
            <input type="submit">
        </form>
    </body>
    <script>
        function insert() {
            displayForm();

            // cập nhật form action thành insert và reset form nếu đã bấm vào edit
            let form = document.querySelector('#formAddEdit');
            form.action = "ProductControllerTest?action=insert";
            form.querySelector('h1').innerText = "Insert";

            form.querySelector("input[name = 'name']").value = null;
            form.querySelector("input[name = 'quantity']").value = null;
            form.querySelector("input[name = 'price']").value = null;

        }
        function displayForm() {
            let form = document.querySelector("#formAddEdit");
            if (form.style.display === 'none') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }

            function update(e) {
                displayForm();

                // cập nhật form action thành update
                let form = document.querySelector('#formAddEdit');
                form.action = "ProductControllerTest?action=update";
                form.querySelector('h1').innerText = "Update";

                //lấy dữ liệu ở hàng mà người dùng bấm 
                let tr = e.closest('tr');
                let id = tr.querySelector("td[name = 'id']").innerText;
                let name = tr.querySelector("td[name = 'name']").innerText;
                let quantity = tr.querySelector("td[name = 'quantity']").innerText;
                let price = tr.querySelector("td[name = 'price']").innerText;


                // Đắp dữ liệu lấy được lên form update
                form.querySelector("input[name = 'id']").value = id;
                form.querySelector("input[name = 'name']").value = name;
                form.querySelector("input[name = 'quantity']").value = quantity;
                form.querySelector("input[name = 'price']").value = price;

            }

        function deleteProduct(e) {
            let form = e.closest('form');
            if (confirm('Do you really want to delete this product?')) {
                form.submit(); // Submit the form if the user confirms
            }
        }
    </script>
</html>
