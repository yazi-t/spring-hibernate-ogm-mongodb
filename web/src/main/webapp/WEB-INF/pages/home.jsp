<%--
  Created by IntelliJ IDEA.
  User: yasitha
  Date: 4/11/17
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:twrapper>

  <jsp:attribute name="page_heading">
    My Blog
  </jsp:attribute>

  <jsp:attribute name="page_body">
    <div class="row">

        <c:forEach var="post" items="${posts}" varStatus="i">
            <div class="col-md-12">
                <h3 style="padding: 20px 0">${post.title}</h3>

                <img src="/uploads/hib-ogm/${post.imgUrl}" class="img-responsive" width="500px" />

                <p style="padding: 20px 0">${post.description}</p>

                <c:forEach var="comment" items="${post.comments}" varStatus="i2">
                    <div class="row">
                        <div class="col-md-6" style="padding: 10px;background: rgb(234, 234, 234) none repeat scroll 0% 0%;border-radius: 4px;margin-bottom: 10px;">
                            <p><a href="#">${comment.email}</a> ${comment.comment}</p>
                            <small>${comment.time}</small>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <form action="<c:url value="save-comment" />" method="post">
                <div class="col-md-6" style="padding: 10px;background: rgb(234, 234, 234) none repeat scroll 0% 0%;border-radius: 4px;margin-bottom: 10px;">
                    <div class="row">
                        <div class="col-md-12">
                            <input type="text" name="comment" class="form-control comment"/>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-md-8">
                            <input type="email" name="email" class="form-control email"/>
                        </div>
                        <input type="hidden" name="postId" value="${post.id}"/>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-default">Post Comment</button>
                        </div>
                    </div>
                </div>
            </form>

            <br>
            <hr>

        </c:forEach>

    </div>
  </jsp:attribute>
</t:twrapper>
