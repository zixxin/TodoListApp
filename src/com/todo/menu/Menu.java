package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("< ToDoList 관리 명령어 사용법 >");
        System.out.println("[add] 항목 추가");
        System.out.println("[del] 항목 삭제");
        System.out.println("[edit] 항목 수정");
        System.out.println("[ls] 전체 목록");
        System.out.println("[find (키워드)] 항목 검색");
        System.out.println("[find_cate (키워드)] 키워드 검색");
        System.out.println("[ls_name_asc] 제목순 정렬");
        System.out.println("[ls_name_desc] 제목역순 정렬");
        System.out.println("[ls_date] 날짜순 정렬");
        System.out.println("[ls_date_desc] 날짜역순 정렬");
        System.out.println("[exit] 종료");
    }
   
    public static void prompt() {
    	System.out.print("\n명령어 입력 -> ");
    }
}
