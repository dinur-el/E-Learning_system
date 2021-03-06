<%@page import="com.oop.model.Course"%>
<%@page import="com.oop.model.Lecturer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.LecturerServiceImpl"%>
<%@page import="com.oop.service.ILecturerService"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>E-Learning Web App</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="row">
        <div class="col-md-8 col-xs-12 col-md-offset-2">

            <h3>My Courses <br /> <small>Enrolled</small></h3>

            <br />

            <!-- a row of course details -- 3 courses -->
            <div class="row">
            <%
			Cookie ck[]=request.getCookies();  
			//for(int i=0;i<ck.length;i++){  
 			String username = ck[0].getValue();
			System.out.println(username);
			//}
			ILecturerService iLecturerService = new LecturerServiceImpl();
			ArrayList<Course> arrayList = iLecturerService.getCourseList(username);
			Cookie ck1=new Cookie("username","");
			ck[0] =null;
			for(Course course : arrayList){
				//System.out.println(course.getName());
			
			%>

                <div class="col-xs-4">
                    <a href="#">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"> <%out.println(course.getName()); %> </h3>
                            </div>
                            <div class="panel-body">
                                Panel body ...
                            </div>
                        </div>
                    </a>
                </div>
                
              <%} %>  
              

            </div>


            <div class="row">

                <div class="col-xs-4">
                    <a href="#">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Panel title</h3>
                            </div>
                            <div class="panel-body">
                                Panel body ...
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-4">
                    <a href="#">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Panel title</h3>
                            </div>
                            <div class="panel-body">
                                Panel body ...
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-4">
                    <a href="#">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Panel title</h3>
                            </div>
                            <div class="panel-body">
                                Panel body ...
                            </div>
                        </div>
                    </a>
                </div>

            </div>

            <div class="row">

                <div class="col-xs-4">
                    <a href="#">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Panel title</h3>
                            </div>
                            <div class="panel-body">
                                Panel body ...
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-4">
                    <a href="#">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Panel title</h3>
                            </div>
                            <div class="panel-body">
                                Panel body ...
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-4">
                    <a href="#">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Panel title</h3>
                            </div>
                            <div class="panel-body">
                                Panel body ...
                            </div>
                        </div>
                    </a>
                </div>

            </div>

        </div>
    </div>

</div><!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
