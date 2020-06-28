package com.project.basic;

import com.project.impel.VoteStudent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合  java.util 包
 */
public class BasicCollection {
    /**
     * 集合接口：java.util.Collection ： 单值集合操作的最大的父接口
     *       public interface Iterable<T> {} JDK 1.5
     *           public interface Collection<E> extends Iterable<E> {} JDK 1.2
     *           关键方法：添加：boolean add(E var1);
     *                   输出（遍历接口）：Iterator<E> iterator();
     *           关键子类：java.util.List<E> : 有序集合，允许有重复的元素
     *                        public interface List<E> extends Collection<E> {}
     *                    java.util.Set<E> : 有序集合，不允许有重复的元素
     *                        public interface Set<E> extends Collection<E> {}
     *                    java.util.Queue<E> : 单端队列，单进单出、先进先出
     *                        public interface Queue<E> extends Collection<E> {}
     */

    /**
     * java.util.Collection 子类接口：
     *     java.util.List<E> : 有序集合，允许有重复的元素
     *          public interface List<E> extends Collection<E> {}
     *          扩充的方法：获取指定索引元素：E get(int var1);
     *                    设置指定索引元素值：E set(int var1, E var2);
     *                    遍历接口：ListIterator<E> listIterator();
     *          子类：java.util.ArrayList<E> 基于数组的实现
     *                   public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {}
     *               java.util.LinkedList<E> 基于链表的实现
     *                   public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable {}
     *               java.util.Vector<E>
     *                   public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {}
     */

    /**
     * java.util.AbstractCollection<E>
     *     public abstract class AbstractCollection<E> implements Collection<E> {}
     * java.util.AbstractList<E>
     *     public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>{}
     * 集合：java.util.ArrayList<E>  基于数组的实现
     *          public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {}
     *          构造方法：public ArrayList(){}
     *                  public ArrayList(int initialCapacity){}
     *                  public ArrayList(Collection<? extends E> c){}
     *      注意：在使用 ArrayList 保存自定义类对象时，使用 contains 与 remove 之前需确保在自定义类中覆写了 equals 方法
     */

    /**
     * java.util.AbstractCollection<E>
     *     public abstract class AbstractCollection<E> implements Collection<E> {}
     * java.util.AbstractList<E>
     *     public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>{}
     * java.util.AbstractSequentialList<E>
     *     public abstract class AbstractSequentialList<E> extends AbstractList<E>{}
     * 集合：java.util.LinkedList<E> 基于链表的实现
     *          public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable {}
     *          构造方法：public LinkedList() {this.size = 0;}
     *                   public LinkedList(Collection<? extends E> c) { this(); this.addAll(c); }
     */

    /**
     * java.util.AbstractCollection<E>
     *     public abstract class AbstractCollection<E> implements Collection<E> {}
     * java.util.AbstractList<E>
     *     public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>{}
     * 集合：java.util.Vector<E>  向量（Vector）：大小能根据需要动态地变化 JDK 1.0
     *          public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {}
     *          构造方法：public Vector() { this(10); }
     *                  public Vector(int initialCapacity) { this(initialCapacity, 0); }
     *                  public Vector(int initialCapacity, int capacityIncrement) {}
     *                  public Vector(Collection<? extends E> c){}
     *      注意：Vector 类中的元素处理方法采用了 synchronized 同步处理，在多线程中是线程安全的，但性能不如 ArrayList 高
     */

    /**
     * java.util.Collection 子类接口：
     *     java.util.Set<E> : 有序集合，不允许有重复的元素
     *          public interface Set<E> extends Collection<E> {}
     *          子类：java.util.HashSet<E>  保存的数据是无序的
     *                   public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {}
     *               java.util.TreeSet<E>  保存的数据是有序的
     *                   public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, Serializable {}
     *     区别与 List ：没有提供 get() 方法来获取指定索引位的元素
     */

    /**
     * java.util.AbstractCollection<E>
     *     public abstract class AbstractCollection<E> implements Collection<E> {}
     * java.util.AbstractSet<E>
     *     public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {}
     * 集合：java.util.HashSet<E> 保存的数据是无序的
     *            public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {}
     *      注意：在使用 HashSet 保存自定义类对象时，是通过 java.lang.Object 类中的 hashCode 与 equals 方法进行重复判断的
     *           方法：对象编码：public native int hashCode();
     *                对象比较：public boolean equals(Object obj) { return this == obj; }
     */

    /**
     * java.util.AbstractCollection<E>
     *     public abstract class AbstractCollection<E> implements Collection<E> {}
     * java.util.AbstractSet<E>
     *     public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {}
     * 集合：java.util.TreeSet<E>  保存的数据是有序的
     *            public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, Serializable {}
     *      注意：1、java.util.TreeSet<E>  是通过 java.lang.Comparable<T> 接口进行重复判断
     *           2、在使用 TreeSet 保存自定义类对象时，需确保在自定义类实现了 java.lang.Comparable<T> 接口，且类中所有属性都需要进行比较
     */

    /**
     *集合输出形式：Iterator 迭代输出
     *            ListIterator 双向迭代输出
     *            Enumeration 枚举输出
     *            foreach 输出 JDK 1.5 类似于数组   for (E e : 集合){//....}
     * 集合遍历接口：java.util.Iterator<E>: 迭代输出
     *                public interface Iterator<E>{}
     *                方法：判断是否有数据：boolean hasNext();
     *                     获取当前数据：E next();
     *                     删除：default void remove() { throw new UnsupportedOperationException("remove"); }
     *            注意：在进行迭代输出时，使用 java.util.Collection 中的 remove() 删除数据时会抛出异常，此时只能使用 java.util.Iterator 中的 remove()
     *
     *            java.util.ListIterator<E>:  双向迭代输出 只最用于 List 集合
     *                public interface ListIterator<E> extends Iterator<E>
     *                方法：判断是否有前一元素：boolean hasPrevious();
     *                     获取当前元素：E previous();
     *
     *           java.util.Enumeration<E>:  JDK 1.0 枚举输出 只作用于 Vector 集合
     *               public interface Enumeration<E>
     *               方法：判断是否有数据：boolean hasMoreElements();
     *                    获取当前数据：E nextElement();
     *
     */

    public static void main(String[] args) {

        /******************** java.util.Collection<E> 集合 *********************/
        System.out.println("******** java.util.Collection<E> 集合 *********");
        List<String> lists = new ArrayList<>(); //元素保存的顺序就是存储的顺序
        lists.add("Decade");
        lists.add("W");
        lists.add("OOO");
        lists.add("Fourze");
        System.out.println(lists); // [Decade, W, OOO, Fourze]
        //JDK 1.8 以后
        lists.forEach((list) -> {
            System.out.print(list + "、");  //Decade、W、OOO、Fourze、
        });
        ListIterator<String> listIterator = lists.listIterator();
        System.out.print("\n由前向后输出：");
        while (listIterator.hasNext()){
            System.out.print(listIterator.next() + "、"); //由前向后输出：Decade、W、OOO、Fourze、
        }
        System.out.print("\n由后向前输出：");
        while (listIterator.hasPrevious()){
            System.out.print(listIterator.previous() + "、");  //由后向前输出：Fourze、OOO、W、Decade、
        }
        System.out.println();
        //方法引用代替消费型接口
        lists.forEach(System.out::println); //Decade\n W\n OOO\n Fourze\n

        Vector<String> vectors = new Vector<>(); //元素保存的顺序就是存储的顺序
        vectors.add("Wizard");
        vectors.add("Gaim");
        vectors.add("Drive");
        vectors.add("Ghost");
        Enumeration<String> enumeration = vectors.elements();
        while (enumeration.hasMoreElements()){
            System.out.print(enumeration.nextElement() + "、");  //Wizard、Gaim、Drive、Ghost、
        }
        System.out.println();
        for (String str: vectors){
            System.out.print(str + "、");  //Wizard、Gaim、Drive、Ghost、
        }
        System.out.println();

        //Arrays[] arrays = new Arrays[lists.size()];
        //System.out.println(arrays.length);
        //数组扩容
        //arrays = Arrays.copyOf(arrays, 10);
        //System.out.println(arrays.length);

        Set<String> hashSet = new HashSet<>(); //元素保存的顺序与存储的顺序无关
        hashSet.add("Decade");
        hashSet.add("W");
        hashSet.add("OOO");
        hashSet.add("Fourze");  //重复的元素，不会被保存，而且不会抛出异常
        hashSet.forEach(System.out::println); //Decade\n W\n Fourze\n OOO\n

        Set<String> treeSet = new TreeSet<>(); //元素保存的顺序与存储的顺序无关，但是根据元素有序(升序)保存
        treeSet.add("Decade");
        treeSet.add("W");
        treeSet.add("OOO");
        treeSet.add("Fourze");  //重复的元素，不会被保存，而且不会抛出异常
        //treeSet.forEach(System.out::println); //Decade\n Fourze\n OOO\n W\n
        Iterator<String> iterator = treeSet.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "、");  //Decade、Fourze、OOO、W、
        }
        System.out.println();


        /******************** java.util.Map<K, V> 集合 *********************/
        System.out.println("******** java.util.Map<K, V> 集合 *********");
        Map<String, Integer> map = Map.of("Decade", 30, "Kiva", 28, "Ex-aid", 25);
        System.out.println(map); //{Decade=30, Ex-aid=25, Kiva=28}

        //子类 java.util.HashMap ，无序存储
        Map<String, Integer> hashMap = new HashMap<>();
        System.out.println(hashMap.put("電王", 22)); //key 不重复， 返回 null
        System.out.println(hashMap.put("電王", 15)); //key 重复， 返回 被替换的数据 22
        hashMap.put("Decade", 30);
        hashMap.put(null, 20);
        hashMap.put("Ghost", null);
        System.out.println(hashMap); //无序存储 {null=20, 電王=15, Ghost=null, Decade=30}
        System.out.println(hashMap.get(null));  //20
        System.out.println(hashMap.get("Ghost"));  //null
        Set<Map.Entry<String, Integer>> setHashMap = hashMap.entrySet();
        // foreach 输出
        System.out.println("----foreach 输出-----");
        for (Map.Entry<String, Integer> entry: setHashMap){
            System.out.println("The Rider is " + entry.getKey() + ", " + entry.getValue() + " years old.");
            System.out.println(entry.getClass().getName()); //java.util.HashMap$Node
        }
        // Iterator 单向迭代输出
        System.out.println("----Iterator 单向迭代输出-----");
        Iterator<Map.Entry<String, Integer>> iteHashMap = setHashMap.iterator();
        while (iteHashMap.hasNext()){
            Map.Entry<String, Integer> entry = iteHashMap.next();
            System.out.println("The Rider is " + entry.getKey() + ", " + entry.getValue() + " years old.");
        }

        //HashMap 存储自定义类 Key
        Map<VoteStudent, String> mapCus = new HashMap<>();
        //在使用 HashMap 保存自定义类 Key 时，自定义类需要覆写 java.lang.Object 类中的 hashCode 与 equals 方法，以便获取对应的 Value
        mapCus.put(new VoteStudent(10, "元十", 0), "有参构造");
        System.out.println(mapCus.get(new VoteStudent(10, "元十", 0))); //有参构造
        Map<String, VoteStudent> mapCusV = new HashMap<>();
        mapCusV.put("有参构造", new VoteStudent(9, "封九", 0));
        System.out.println(mapCusV.get("有参构造")); //9: 封九[0]

        //子类 java.util.LinkedHashMap<K, V> 基于链表存储，存储的顺序为元素添加的顺序
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("電王", 15);
        linkedHashMap.put("Decade", 30);
        linkedHashMap.put("Ghost", 26);
        linkedHashMap.put(null, 22);
        linkedHashMap.put("Zi-O", 20);
        System.out.println(linkedHashMap); // {電王=15, Decade=30, Ghost=26, null=22, Zi-0=20}

        // 子类 java.util.Hashtable<K, V> 存储的 Key 或 Value 不能为 null ，否则抛出 NullPointerException
        Map<String, Integer> hashTable = new Hashtable<>();
        hashTable.put("電王", 15);
        hashTable.put("Decade", 30);
        hashTable.put("Ghost", 26);
        hashTable.put("Zi-0", 20);
        System.out.println(hashTable); // 无序存储 {Zi-0=20, Ghost=26, 電王=15, Decade=30}

        //子类 java.util.TreeMap<K, V> 存储的 Key 不能为 null，Value 允许为 null
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("電王", 15);
        treeMap.put("Decade", 30);
        treeMap.put("Ghost", 26);
        treeMap.put("Build", null);
        treeMap.put("Zi-O", 20);
        System.out.println(treeMap); // {Build=null, Decade=30, Ghost=26, Zi-0=20, 電王=15}

        //栈：java.util.Stack<E> 先进后出的数据结构
        Stack<String> stack = new Stack<>();
        stack.push("Decade");  //入栈
        stack.push("W");
        stack.push("OOO");
        stack.push("Fourze");
        System.out.println(stack); //[Decade, W, OOO, Fourze]
        System.out.println(stack.pop()); //出栈并删除 Fourze
        System.out.println(stack.peek()); //出栈不删除 OOO

        //队列 java.util.Queue<E> : 单端队列，单进单出、先进先出
        //基于链表的实现，存储的顺序是添加的顺序
        Queue<String> queue = new LinkedList<>();
        queue.offer("Kuuga");
        queue.offer("Kuuga");
        queue.offer("Agito");
        queue.offer("Dragon");
        System.out.println(queue);  //[Kuuga, Kuuga, Agito, Dragon]
        System.out.println(queue.peek());  //Kuua  取出不删除
        System.out.println(queue.peek());  //Kuua  取出并删除

        //优先级队列：java.util.PriorityQueue<E>  有序存储，依据存储对象"升序"存储
        Queue<String> priQueue = new PriorityQueue<>();
        priQueue.offer("Kuuga");
        priQueue.offer("Agito");
        priQueue.offer("Dragon");
        System.out.println(priQueue); // [Agito, Kuuga, Dragon]
        System.out.println(priQueue.peek()); // Agito

        //java.util.Properties 属性操作：（*.properties 资源文件，内容以" key = value" 格式保存）
        Properties prop = new Properties();
        prop.setProperty("1999-2000", "Kuuga");
        prop.setProperty("2000-2001", "Agito");
        prop.setProperty("2009-2010", "Decade");
        prop.setProperty("2019-2020", "Zi-O");
        System.out.println(prop); // 无序存储 {2009-2010=Decade, 2000-2001=Agito, 1999-2000=Kuuga, 2019-2020=Zi-0}
        System.out.println(prop.get("2009-2010")); // Decade

        String basePath = "/Users/murongyunge/Desktop/IntelliJ/JavaProject";
        String filePath = basePath.replaceAll("/", File.separator);
        try {
            Properties propFile = new Properties();
            propFile.setProperty("1999-2000", "Kuuga");
            propFile.setProperty("2000-2001", "Agito");
            propFile.setProperty("2006-2007", "電王"); //2006-2007=\u96FB\u738B
            propFile.setProperty("2009-2010", "Decade");
            propFile.setProperty("2019-2020", "Zi-O");
            File file = new File(filePath, File.separator + "res" + File.separator + "Rider.properties");
            //保存资源文件，如果文件不存在，则自动创建 中文字会自动转码
            propFile.store(new FileOutputStream(file), "Rider 播出年份");  //注释
            //读取资源文件
            Properties loadFile = new Properties();
            loadFile.load(new FileInputStream(file));
            System.out.println(loadFile.get("2006-2007")); //電王
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> riders = new ArrayList<>();
        //使用集合工具类 Collections 给集合添加数据
        Collections.addAll(riders, "Ryuki", "Faiz", "Blade", "Hibiki", "Kabuto", "Den-o");
        //对集合元素进行流式分析
        Stream<String> stream = riders.stream();
        //System.out.println(stream.count()); //输出元素个数 使用分析操作之后 stream 自动关闭
        //System.out.println(stream.filter((str) -> { return str.toLowerCase().contains("k"); } ).count()); //3
        //获取集合中满足要求的 Stream 对象
        Stream<String> filter = stream.filter((str) -> {return str.toLowerCase().contains("k");});
        //数据采集：将满足条件的数据单独转成 List 集合
        //List<String> filList = filter.collect(Collectors.toList());
        //System.out.println(filList);  //[Ryuki, Hibiki, Kabuto]
        List<String> filList = filter.skip(2).limit(2).collect(Collectors.toList());
        System.out.println(filList);  //[Kabuto]

        //订单分析
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("MacBook", 9999.99, 2));
        orders.add(new Order("iPhone 11 Plus", 8888.88, 5));
        orders.add(new Order("iPhone 8", 4688.88, 3));
        orders.add(new Order("iPad", 3899.88, 5));
        orders.add(new Order("iWatch", 899.88, 10));
        DoubleSummaryStatistics stat = orders.stream().filter((order -> {return
                order.getName().toLowerCase().contains("ip");
        })).mapToDouble(order -> order.getPrice() * order.getAmount()).summaryStatistics();
        System.out.println("购买数量：" + stat.getCount());
        System.out.println("购买总价：" + stat.getSum());
        System.out.println("平均花费：" + stat.getAverage());
        System.out.println("最高消费：" + stat.getMax());
        System.out.println("最低消费：" + stat.getMin());

    }


    /**
     * 集合接口：java.util.Map<K, V>  双值集合操作的最大的父接口
     *         public interface Map<K, V> {}
     *         关键方法：保存数据：V put(K var1, V var2);
     *                 根据 Key 获取 Value：V get(Object key);
     *                 将 Map 集合转为 set 集合：Set<Map.Entry<K, V>> entrySet();
     *                 判断是否存在指定 Key：boolean containsKey(Object var1);
     *                 将 Map 集合中所有 Key 转成 Set 集合：Set<K> keySet();
     *                 根据指定 Key 删除 Value：V remove(Object key);
     *         常见子类：java.util.HashMap<K, V>  无序存储
     *                     public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable{}
     *                  java.util.LinkedHashMap<K, V> 基于链表存储，存储的顺序为元素添加的顺序
     *                     public class LinkedHashMap<K, V> extends HashMap<K, V> implements Map<K, V>
     *                  java.util.Hashtable<K, V>  无序存储
     *                     public class Hashtable<K, V> extends Dictionary<K, V> implements Map<K, V>, Cloneable, Serializable {}
     *                  java.util.TreeMap<K, V>  有序存储，依据 Key 对象升序存储
     *                     public class TreeMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Cloneable, Serializable {}
     *        HashMap、TreeMap 与 HashTable 区别：
     *            1、HashMap 中的方法是异步的（非线程安全），且存储的 Key 或 Value 允许为 null；
     *            2、HashTable 中的方法是同步（synchronized）的（线程安全），且存储的 Key 或 Value 不能为 null，否则抛出 NullPointerException；
     *            3、TreeMap 中的方法是异步的（非线程安全），存储的 Key 不能为 null，Value 允许为 null，且数据按 Key 对象升序存储；
     */

    /**
     * 集合接口：java.util.Map<K, V>  双值集合操作的最大的父接口
     *             public interface Map<K, V> {}
     *             内部接口：public interface Entry<K, V> {}
     *                     关键方法：获取 Key：K getKey();
     *                              获取 Value：V getValue();
     */

    /**
     * java.util.AbstractMap<K, V>
     *     public abstract class AbstractMap<K, V> implements Map<K, V>
     * 集合：java.util.HashMap<K, V>  无序存储
     *          public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable{}
     *          构造方法：public HashMap() { this.loadFactor = 0.75F; }
     *      HashMap 扩容操作：1、创建时，初始容量 "DEFAULT_INITIAL_CAPACITY = 16" 为16，即最大的存储元素数量；
     *                       2、当存储的元素数量超过 "当前容量 * 阈值（DEFAULT_LOAD_FACTOR = 0.75F）"时，进行容量扩容；
     *                       3、新的容量大小为原始容量的 2 倍（newCap = oldCap << 1）
     *              工作原理：HashMap 数据存储利用 Node 类完成，当存储的元素数量小于阈值（TREEIFY_THRESHOLD = 8;）时，
     *                      采用链表形式（时间复杂度O(n)）存储；大于该阈值时，则采用红黑树（时间复杂度O(logn)）进行存储，
     *                      利用左旋与右旋保证数据查询的性能
     *              Hash 冲突（Hash 值相同）；为保证程序运行的正常，HashMap 会在 Hash 冲突的地方将冲突的内容转为链表保存
     *              注意：在使用 HashMap 保存自定义类 Key 时，自定义类需要覆写 java.lang.Object 类中的 hashCode 与 equals 方法，以便获取对应的 Value
     *                         方法：对象编码：public native int hashCode();
     *                              对象比较：public boolean equals(Object obj) { return this == obj; }
     */

    /**
     * java.util.AbstractMap<K, V>
     *     public abstract class AbstractMap<K, V> implements Map<K, V>
     * java.util.HashMap<K, V>  无序存储
     *     public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable{}
     * 集合：java.util.LinkedHashMap<K, V> 基于链表存储，存储的顺序为元素添加的顺序
     *          public class LinkedHashMap<K, V> extends HashMap<K, V> implements Map<K, V>
     *      注意：因为基于链表形式存储，存储的数据量要求不大，防止时间复杂度的攀升
     */

    /**
     * java.util.Dictionary<K, V>
     *     public abstract class Dictionary<K, V> {}
     * 集合：java.util.Hashtable<K, V>  无序存储  JDK 1.0
     *          public class Hashtable<K, V> extends Dictionary<K, V> implements Map<K, V>, Cloneable, Serializable {}
     *          构造函数：public Hashtable() { this(11, 0.75F); }
     */

    /**
     * java.util.AbstractMap<K, V>
     *     public abstract class AbstractMap<K, V> implements Map<K, V>
     * 集合：java.util.TreeMap<K, V>  有序存储，依据 Key 对象升序存储
     *           public class TreeMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Cloneable, Serializable {}
     */

    /**
     * 集合工具类
     *    栈：java.util.Stack<E> 先进后出的数据结构
     *           public class Stack<E> extends Vector<E> {}
     *           构造方法：public Stack() {}
     *           关键方法：入栈：public E push(E item) {}
     *                   出栈：public synchronized E pop() {}  // 出栈并删除，不能进行空栈操作，否则抛出 EmptyStackException
     *                        public synchronized E peek() {} // 出栈不删除，不能进行空栈操作，否则抛出 EmptyStackException
     *
     *    队列：java.util.Queue<E> : 单端队列，单进单出、先进先出
     *             public interface Queue<E> extends Collection<E> {}
     *             添加数据：boolean offer(E var1);
     *                      boolean add(E var1);
     *             取出数据：E poll();  // 取出并删除
     *                      E peek();  // 取出不删除
     *             **如果将队列应用于多线程的"生产者与消费者"模型上，那么"生产者"可以不必等待"消费者"消费完再生产，而是可以将生产的数据保存在队列中
     *             子类：1、java.util.LinkedList<E> 基于链表的实现，存储的顺序是添加的顺序
     *                          public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable {}
     *                  2、java.util.AbstractQueue<E>
     *                         public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> {}
     *                     优先级队列：java.util.PriorityQueue<E>  有序存储，依据存储对象"升序"存储
     *                                  public class PriorityQueue<E> extends AbstractQueue<E> implements Serializable
     *    Properties 属性操作：（*.properties 资源文件，内容以" key = value" 格式保存）
     *        java.util.Properties:
     *              public class Properties extends Hashtable<Object, Object> {}
     *              构造方法：public Properties() { this((Properties)null, 8); }
     *              关键方法：设置属性：public synchronized Object setProperty(String key, String value) { return this.put(key, value);}
     *                       获取属性：public String getProperty(String key) {} // Key 不存在返回 null
     *                               public String getProperty(String key, String defaultValue) {}  // Key 不存在返回 defaultValue
     *                       通过输出流输出属性：public void store(OutputStream out, String comments) throws IOException {}
     *                       通过输入流读取属性：public synchronized void load(InputStream inStream) throws IOException {}
     *
     *    Collections: 集合类的一个工具类/帮助类，其中提供了一系列静态方法，用于对集合中元素进行排序、搜索以及线程安全等各种操作
     *               java.util.Collections: 用于各个集合的操作  JDK 1.2
     *                     public class Collections {}
     *                     静态方法：给指定集合添加任意数据：public static <T> boolean addAll(Collection<? super T> c, T... elements) {}
     *                              对 List 集合元素进行反转排序：public static void reverse(List<?> list) {}
     *                              对 List 集合升序排序：public static <T extends Comparable<? super T>> void sort(List<T> list){}
     *                                                  public static <T> void sort(List<T> list, Comparator<? super T> c) {}
     *                              二分查找 List 集合：前提是先进行 List 集合排序
     *                                     public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {}
     *
     *   java.util.stream.Stream<T> : 对集合元素进行数据流式分析  JDK 1.8
     *       public interface Stream<T> extends BaseStream<T, Stream<T>> {}
     *       获取 Stream 接口对象：通过 java.util.Collection 集合中的方法获得
     *                           1、default Stream<E> stream() {} //单线程使用
     *                           2、default Stream<E> parallelStream() //多线程使用
     *       获取集合中满足要求的 Stream 对象：Stream<T> filter(Predicate<? super T> var1);
     *       数据采集：将满足条件的数据单独转成 List 集合：<R, A> R collect(Collector<? super T, A, R> var1);
     *       数据分页：设置取出的最大数据量：Stream<T> limit(long var1);
     *                跳过指定的数据量：Stream<T> skip(long var1);
     *
     *   MapReduce 基础模型
     */

    //订单模型
    static class Order{
        String name;
        double price;
        int amount;

        public Order(String name, double price, int amount) {
            this.name = name;
            this.price = price;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

}
