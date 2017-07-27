<%--
  Created by IntelliJ IDEA.
  User: yasitha
  Date: 4/27/17
  Time: 9:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:twrapper>

  <jsp:attribute name="page_heading">
    Add New Post
  </jsp:attribute>

  <jsp:attribute name="page_body">
    <div class="row">
        <div class="col-md-8">
            <br/>
            <h3 class="text-center">Add new post</h3>
            <br/>
            <form class="form-horizontal" action="<c:url value="/save" />" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <label class="control-label col-sm-2" for="txtTitle">Title</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="txtTitle" name="title" placeholder="Enter title" required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="txtTitle">Title</label>

                    <div class="col-sm-10">
                        <textarea class="form-control" id="txtDescription" name="description" rows="5"
                                  placeholder="Enter description" required="required"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="txtTitle">Image</label>

                    <div class="col-sm-10">
                        <input type="file" class="form-control" name="image"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="txtTitle"></label>

                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-default">Save</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
  </jsp:attribute>
</t:twrapper>
