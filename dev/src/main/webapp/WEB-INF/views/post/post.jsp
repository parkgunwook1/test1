<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>${post.title}</title>
    <style>
        .post-container {
            max-width: 800px;
            margin: 2rem auto;
            padding: 2rem;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }

        .post-header {
            border-bottom: 2px solid #eee;
            padding-bottom: 1rem;
            margin-bottom: 2rem;
        }

        .post-title {
            font-size: 2rem;
            margin-bottom: 1rem;
            color: #333;
        }

        .post-info {
            display: flex;
            justify-content: space-between;
            color: #666;
            font-size: 0.9rem;
        }

        .post-image {
            width: 100%;
            max-height: 500px;
            object-fit: contain;
            margin: 1rem 0;
            border-radius: 4px;
        }

        .post-content {
            line-height: 1.6;
            margin: 2rem 0;
            color: #444;
        }

        .button-group {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
            padding-top: 1rem;
            border-top: 1px solid #eee;
        }

        .btn {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }

        .btn-edit {
            background-color: #4CAF50;
            color: white;
        }

        .btn-delete {
            background-color: #f44336;
            color: white;
        }

        .btn-back {
            background-color: #2196F3;
            color: white;
        }

        .comments-section {
            margin-top: 3rem;
        }

        .comment {
            padding: 1rem;
            border-bottom: 1px solid #eee;
        }

        .comment-form {
            margin-top: 2rem;
        }

        .comment-input {
            width: 100%;
            padding: 1rem;
            margin-bottom: 1rem;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="post-container">
        <div class="post-header">
            <h1 class="post-title">${post.title}</h1>
            <div class="post-info">
                <span>작성자: ${post.author}</span>
                <span>조회수: ${post.viewCount}</span>
                <span>
                    작성일: <fmt:formatDate value="${post.createDate}" pattern="yyyy-MM-dd HH:mm"/>
                </span>
            </div>
        </div>

        <c:if test="${not empty post.image}">
            <img src="${post.image}" alt="게시글 이미지" class="post-image">
        </c:if>

        <div class="post-content">
            ${post.content}
        </div>
        <!-- userId 세션스코프에 담고 userId와 게시글 작성한 Id와 동일했을 시에 아래 목록이 보이게 해줘야함-->
        <div class="button-group">
            <button class="btn btn-back" onclick="location.href='/'">목록으로</button>
            <c:if test="${sessionScope.userId eq post.authorId}">
                <button class="btn btn-edit" onclick="location.href='/post/edit/${post.postId}'">수정</button>
                <button class="btn btn-delete" onclick="deletePost('${post.postId}')">삭제</button>
            </c:if>
        </div>

        <div class="comments-section">
            <h3>댓글 (${post.comments.size()})</h3>
            <c:forEach items="${post.comments}" var="comment">
                <div class="comment">
                    <strong>${comment.author}</strong>
                    <p>${comment.content}</p>
                    <small>
                        <fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm"/>
                    </small>
                </div>
            </c:forEach>

            <form class="comment-form" onsubmit="return submitComment(event)">
                <textarea class="comment-input" 
                          placeholder="댓글을 입력하세요" 
                          rows="3"
                          required></textarea>
                <button class="btn btn-edit" type="submit">댓글 작성</button>
            </form>
        </div>
    </div>

    <script>
        function deletePost(postId) {
            if (confirm('정말로 삭제하시겠습니까?')) {
                fetch(`/api/posts/${postId}`, {
                    method: 'DELETE'
                }).then(response => {
                    if (response.ok) {
                        alert('게시글이 삭제되었습니다.');
                        location.href = '/';
                    }
                });
            }
        }

        function submitComment(event) {
            event.preventDefault();
            const content = event.target.querySelector('textarea').value;
            
            fetch(`/api/posts/${post.postId}/comments`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ content: content })
            }).then(response => {
                if (response.ok) {
                    location.reload();
                }
            });
            
            return false;
        }
    </script>
</body>
</html>