import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Lab10 {
	

	
	public static <E> int count(Stack<E> s, E item){
		int count = 0;
		Stack<E> s2 = new Stack<E>();
		while (!s.empty()) {
			s2.push(s.pop());
			if (s2.peek().equals(item))
				count++;
			}
		while (!s2.empty()) {
			s.push(s2.pop());
		}
		return count;
	}
	
	public static <E> void reverse(Stack<E> s) {
		Stack<E> s2 = new Stack<E>();
		Stack<E> s3 = new Stack<E>();
		while (!s.empty()) 
			s2.push(s.pop());
		
		while (!s2.empty()) 
			s3.push(s2.pop());
		
		while (!s3.empty()) 
			s.push(s3.pop());
	}
	 
	public static <E> void cutTheLine(Queue<E> q, int index) {
		Queue<E> q2 = new LinkedList<E>();
		Queue<E> head = new LinkedList<E>();
		int idx = 0;
		
		while (q.peek() != null) {
			if (idx==index)
				head.offer(q.poll());
			else 
				q2.offer(q.poll());
			idx++;
		}
		
		q.offer(head.peek());
		while (q2.peek() != null) 
			q.offer(q2.poll());
		
	}
	
	public static <E> Queue<E> zipQueues(Queue<E> q1, Queue<E> q2) {
		Queue<E> zip = new LinkedList<E>();
		Queue<E> tempq1 = new LinkedList<E>();
		Queue<E> tempq2 = new LinkedList<E>();
		
		int sizeMax = Math.max(q1.size(), q2.size());
		// to optimize space and time efficiency and fix a null edge case
		if (q1.peek() == null)
			return q2;
		
		else if (q2.peek() == null)
			return q1;
		
		else {
		for (int i = 0; i < sizeMax; i++) {
			if (q1.peek() != null) {
				E e1 = q1.poll();
				zip.offer(e1);
				tempq1.offer(e1);
			}
			if (q2.peek() != null) {    
				E e2 = q2.poll();
				zip.offer(e2);
				tempq2.offer(e2);
			}
		}
		while (tempq1.peek() != null)
			q1.offer(tempq1.poll());
		while (tempq2.peek() != null)
			q2.offer(tempq2.poll());
		return zip;
		}
	}
}