<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MVC02 Board Detail **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/writeBoard.css" >
</head>
<body>
<c:set var="b" value="${ requestScope.apple }"></c:set>
   <!-- 컨테이너 -->
   <div class="container">
      <!-- 전체 마진, 패딩 -->
      <div class="content">
         <!-- 컨텐츠 wrap -->
         <!-- <form action=".https://lastteamproject.web.app/news_n_event/"></form> -->
		<h2 style="font-size:400%;">** Spring_MVC02 Board Detail **</h2>
		<br>
         <div class="boardWriteForm">
            <div class="board_wrap">
               <!-- 제목영역 -->
               <div class="title_wrap">
                  <div class="title">
                     <strong>제목</strong>
                  </div>
                  <div class="board_category">
                     <div class="title_input_box">
                        <input value="${ b.title }" type="text" readonly>
                     </div>
                  </div>
               </div>
               <!-- 본문영역 -->
               <div class="main_text_wrap">
                  <div class="tool_bar_top">
                     <div class="list_n_aline_tool_wrap">
                        <div class="list_tool_wrap">
                        </div>
                     </div>
                     <div class="etc_tool_wrap">
                        <div class="more_insert_tool_button">
                        </div>
                        <div class="functional_tool_button">
                           <button class="revert_button"><i class="fa-solid fa-reply"></i></button>
                           <button class="forward_button"><i class="fa-solid fa-share"></i></button>
                           <button class="etc_button"><i class="fa-solid fa-ellipsis-vertical"></i></button>
                        </div>
                     </div>
                  </div>
                  <div class="main_text_line"></div>
                  <div class="main_text">
                     <div class="main_text_body">
                        <textarea name="" id="main_text" cols="30" rows="10" readonly>${ b.content }</textarea>
                     </div>
                  </div>
                  <div class="tool_bar_bottom">
                     <span class="letter_num">글번호 : ${ b.seq }</span> &nbsp;&nbsp;&nbsp;
                     <span class="letter_num">작성자 : ${ b.id }</span> &nbsp;&nbsp;&nbsp;
                     <span class="letter_num">작성 시간 : ${ b.regdate }</span>
                  </div>
               </div>
               <!-- 등록/취소 영역 -->
            </div>
            <div>
            <c:if test="${sessionScope.loginID eq b.id}">
               <div class="push_cancel_wrap">
                  <a href="detail?jCode=U&seq=${ b.seq }" id="push_button"><span>수정</span></a>
                  <a href="" id="cancel_button"><span>삭제</span></a>
               </div>
            </c:if>
            <br>
               <div class="push_cancel_wrap">
                  <a href="boardList" id="push_button">글목록</a>
               </div>
            </div>
         </div>
      </div>
   </div>
</body>
</body>
</html>