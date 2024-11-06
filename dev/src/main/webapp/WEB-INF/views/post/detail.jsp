<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
      
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/main/header.jsp"/>
     <div class="container">
        <h1 th:text="${post.title}">제목</h1>
        <p th:text="${post.content}">내용</p>
        <p th:text="${post.author}">작성자</p>
        <p th:text="${post.createDate}">작성일</p>
        <c:if test="${post.modifiedDate != null}"> 
            <p th:text="${post.modifiedDate}">수정일</p>
        </c:if>

        <p th:text="${post.viewCount}">조회수</p>
        
        <!--이미지 출력-->
     </div>
<jsp:include page="/WEB-INF/views/main/footer.jsp"/>
</body>
</html>
