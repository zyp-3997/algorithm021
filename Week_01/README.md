##学习笔记
学习总结：https://blog.csdn.net/weixin_44860598/article/details/110771504
  作业：
  用 add first 或 add last 这套新的 API 改写 Deque 的代码
   ```java
public class Deque{
  public void updateDeque(){
       Deque<String> deque=new LinkedList<>();
       deque.addFirst("a");
       deque.addFirst("b");
       deque.addFirst("c");
       deque.offerFirst("d");
       deque.offerFirst("e");
       deque.offerFirst("f");
       System.out.println(deque);
       String str=deque.peek();
       System.out.println("peek出："+str);
       System.out.println("peek后："+deque);
       String str2=deque.element();
       System.out.println("element出："+str2);
       System.out.println("element后："+deque);
       while (deque.size()>0){
           System.out.println(deque.pop());
       }
       System.out.println("pop后："+deque);
   }}
   ```
**Queue源码**
区别：
add，remove，element这组抛出异常
offer,poll,peek这组返回值
   ```java
   public interface Queue<E> extends Collection<E> {
       boolean add(E e);//添加
       boolean offer(E e);//添加
       E remove();//移除
       E poll();///移除
       E element();//查看
       E peek();//查看
   }
   ```
   
**PriorityQue源码**
```java
public class PriorityQueue<E> extends AbstractQueue<E>
    implements java.io.Serializable {
 transient Object[] queue; 
 private int size = 0;
  
 public boolean add(E e) {
        return offer(e);
    }
 public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size;
        if (i >= queue.length)
            grow(i + 1);//长度不够，进行扩容
        size = i + 1;
        if (i == 0)
            queue[0] = e;//如果队列没有元素，加在队列头部
        else
            siftUp(i, e);
        return true;
    }
    public E peek() {//如果不为空，查看队列第一个元素
        return (size == 0) ? null : (E) queue[0];
    }
    //移除元素
    public E poll() {
        if (size == 0)
            return null;
        int s = --size;
        modCount++;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0)
            siftDown(0, x);
        return result;
    }
    //移除元素
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1)
            return false;
        else {
            removeAt(i);
            return true;
        }
    }
}
```
