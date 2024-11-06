<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .board {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 1rem;
            padding: 1rem;
            max-width: 1200px;
            margin: 0 auto;
            flex-grow: 1;
        }
        .board-item {
            border: 1px solid #ccc;
            padding: 1rem;
            background-color: #f9f9f9;
            text-align: center;
        }

        .button-container {
            text-align: center;
            margin: 1rem;
        }
        .button {
            padding: 0.5rem 1rem;
            font-size: 1rem;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
        .board {
            gap: 20px;
        }
        /* 모달 배경 스타일 */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }
        /* 모달 콘첸츠 스타일 */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }
        /* 닫기 버튼 스타일*/
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }
        .close:hover, .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        form label, form input, form button {
            display: block;
            margin: 10px 0;
            width: 100%;
        }
        form button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 5px;
        }
        form button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"/>

    <div class="button-container">
        <button class="button" onclick="writeModal()">글쓰기</button>
    </div>

    <!-- 모달 -->
     <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>

            <!-- 글쓰기 툴 -->
             <form action="/post/write" method="post" enctype="multipart/form-data">
                 <label for="image">Image:</label>
                 <input type="file" id="image" name="image" accept="image/*">

                 <label for="title">Title:</label>
                 <input type="text" id="title" name="title" required />

                <label for="content">Content:</label>
                <input type="text" id="content" name="content" required />

                 <button type="submit">Submit</button>
             </form>
        </div>
     </div>

    <div class="board">
            <c:forEach items= "${posts}" var="post">
                <div class="board-item">
                    <img src="${post.image}" alt="게시물 이미지" onclick="goToPost(${post.id})">
                    <p>${post.image}</p>
                    <p>${post.title}</p>
                </div>
            </c:forEach>
        </div>

    <script>
        // 모달 열기
        function writeModal() {
            document.getElementById("myModal").style.display = "block";
        }
        // 모달 닫기
        function closeModal() {
            document.getElementById("myModal").style.display = "none";
        }

        // 모달 외부 클릭 시 닫기
        window.onclick = function(event) {
            const modal = documnet.getElementById("myModal");
            if (event.target === modal) {
                modal.style.display = "none";
            }
        }
    </script>

<jsp:include page="/WEB-INF/views/main/footer.jsp"/>
</body>
</html>
