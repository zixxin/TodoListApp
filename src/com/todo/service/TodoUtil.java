package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	public static void createItem(TodoList list) {
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n"
				+ "제목 입력 -> ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			System.out.println("제목을 수정해주세요.");
			return;
		}
		
		sc.nextLine();
		
		System.out.print("카테고리 입력 -> ");
		category = sc.next();
		
		sc.nextLine();
		
		System.out.print("내용 입력 -> ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일자 입력 (yyyy/mm/dd) -> ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, category, desc, due_date);
		list.addItem(t);
		System.out.println("[항목 추가 완료]");
	}

	public static void deleteItem(TodoList l) {
		int count = 0;
		int num;
		String whe;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n"
				+ "삭제할 항목의 번호 입력 -> ");
		num = sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			count ++;
			if (count == num) {
				System.out.print(count + ".");
				System.out.println(item.toString());
				
				System.out.print("위 항목을 삭제하시겠습니까? (y/n) -> ");
				whe = sc.next();
				if (whe.equals("y")) {
					l.deleteItem(item);
					System.out.println("[항목 삭제 완료]");
					break;
				}
				else break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		int num;
		int count = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n"
				+ "수정할 항목의 번호 입력 -> ");
		num = sc.nextInt();  
		for (TodoItem item : l.getList()) {
			count ++;
			if (count == num) {
				System.out.print(count + ".");
				System.out.println(item.toString()+"\n");
				break;
			}
		}

		System.out.print("새 제목 입력 -> ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다!");
			System.out.println("제목을 수정해주세요.");
			return;
		}
		
		System.out.print("새 카테고리 입력 -> ");
		String new_category = sc.next();
		
		sc.nextLine();
		
		System.out.print("새 내용 입력 -> ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일자 입력 (yyyy/mm/dd) -> ");
		String new_due_date = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (count == num) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_category, new_description, new_due_date);
				l.addItem(t);
				System.out.println("[항목 수정 완료]");
				break;
			}
		}

	}

	public static void listAll(TodoList l) {
		int count1 = 0;
		int num = 1;
		
		for (TodoItem item : l.getList()) {
			count1 ++;
		}
		System.out.println("[전체 목록, 총 "+count1+"개]");
		
		for (TodoItem item : l.getList()) {
			System.out.print(num + ".");
			num ++;
			System.out.println(item.toString());
		}
	}
	
	public static void findItem(TodoList l) {
		int count = 0;
		int num = 1;
		String find;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 검색]\n"
				+ "찾을 항목의 키워드 입력 -> ");
		find = sc.next();  
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().contains(find) || item.getDesc().contains(find)) {
				System.out.print(num + ".");
				num ++;
				count ++;
				System.out.println(item.toString());
			}
		}
		System.out.println("\n총 "+count+"개의 항목이 검색됨");
	}
	
	public static void findKeywordItem(TodoList l) {
		int count = 0;
		int num = 1;
		String find;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[키워드 검색]\n"
				+ "검색할 키워드 입력 -> ");
		find = sc.next();  
		
		sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (find.equals(item.getCategory())) {
				System.out.print(num + ".");
				num ++;
				count ++;
				System.out.println(item.toString());
			}
		}
		System.out.println("\n총 "+count+"개의 항목이 검색됨");
	}
	
	public static void viewCategory(TodoList l) {
		int count = 0;
		
		for (TodoItem item : l.getList()) {
			count ++;
			System.out.print(item.getCategory() + " / ");
		}
		System.out.println("총 "+count+"개의 카테고리가 검색됨");
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("\nTodoList를 " + filename + "에 저장 완료!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		//BufferedReader, FileReader, StringTokenizer
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String category = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, category, desc, due_date, current_date);
				l.addItem(t);
			}
			br.close();
			System.out.println(filename + "의 TodoList를 로딩 완료!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
