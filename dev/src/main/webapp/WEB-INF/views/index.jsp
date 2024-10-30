<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        .board-item img {
            cursor: pointer;
            max-width: 100%;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"/>

    <div class="button-container">
        <button class="button" onclick="writePost()">글쓰기</button>
    </div>

    <!-- 하드 코딩 수정 방식
     
    <div class="board">
        <c:forEach items= "$posts}" var="post">
            <div class="board-item">
                <img src="${post.imgUrl}" alt="게시물 이미지" onclick="goToPost(${post.id})">
                <p>${post.title}</p>
            </div>
        </c:forEach>
    </div>    
    -->


    <div class="board">
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 1 이미지" onclick="goToPost(1)">
            <p>게시물 1</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 2 이미지">
            <p>게시물 2</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 3 이미지">
            <p>게시물 3</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 4 이미지">
            <p>게시물 4</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 5 이미지">
            <p>게시물 5</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 6 이미지">
            <p>게시물 6</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 7 이미지">
            <p>게시물 7</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 8 이미지">
            <p>게시물 8</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 9 이미지">
            <p>게시물 9</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 10 이미지">
            <p>게시물 10</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 11 이미지">
            <p>게시물 11</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 12 이미지">
            <p>게시물 12</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 13 이미지">
            <p>게시물 13</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 14 이미지">
            <p>게시물 14</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 15 이미지">
            <p>게시물 15</p>
        </div>
        <div class="board-item">
            <img src="placeholder.jpg" alt="게시물 16 이미지">
            <p>게시물 16</p>
        </div>
    </div>

    <script>
        function writePost() {
            alert("글쓰기 버튼 클릭됨!");
        }

        function goTopost(postId) {
            // 상세 페이지로 이동
            window.location.href = '/post/detail?id=' + postId;
        }
    </script>

<jsp:include page="/WEB-INF/views/main/footer.jsp"/>
</body>
</html>
