package collections.utilities;


import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.*;

/**
 * 强大的集合工具类：java.util.Collections中未包含的集合工具
 * @author
 * @create 2018-04-06 上午12:10
 **/
public class CollectionUtilities {

    /**
     * 静态工厂方法
     */
    public static void staticConstructors() {

        // Guava提供了能够推断范型的静态工厂方法
        List<Integer> list = Lists.newArrayList();
        Map<String, String> map = Maps.newLinkedHashMap();

        // 用工厂方法模式，我们可以方便地在初始化时就指定起始元素
        Set<Integer> copySet = Sets.newHashSet(1, 2);
        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");

        // 通过为工厂方法命名（Effective Java第一条），我们可以提高集合初始化大小的可读性
        List<Integer> exactly100 = Lists.newArrayListWithCapacity(100);
        List<Integer> approx100 = Lists.newArrayListWithExpectedSize(100);
        Set<Integer> approx100Set = Sets.newHashSetWithExpectedSize(100);

        // Guava引入的新集合类型没有暴露原始构造器，也没有在工具类中提供初始化方法。而是直接在集合类中提供了静态工厂方法
        Multiset<String> multiset = HashMultiset.create();

    }

    /**
     * Iterables工具类
     */
    public static void iterable() {
        /**
         * 常规方法
         */
        // 串联多个iterables的懒视图
        // 懒视图意味着如果还没访问到某个iterable中的元素，则不会对它进行串联操作
        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6));
        // [1, 2, 3, 4, 5, 6]
        System.out.println(concatenated);

        // 返回对象在iterable中出现的次数
        int num = Iterables.frequency(concatenated, 1);
        // 1
        System.out.println(num);

        // 把iterable按指定大小分割，得到的子集都不能进行修改操作
        Iterable<List<Integer>> partition = Iterables.partition(concatenated, 2);
        // [[1, 2], [3, 4], [5, 6]]
        System.out.println(partition);

        // 返回iterable的第一个元素，若iterable为空则返回默认值
        int firstValue = Iterables.getFirst(concatenated, 0);
        // 1
        System.out.println(firstValue);

        // 返回iterable的最后一个元素，若iterable为空则抛出NoSuchElementException
        int lastValue = Iterables.getLast(concatenated, 0);
        // 6
        System.out.println(lastValue);

        // 如果两个iterable中的所有元素相等且顺序一致，返回true
        Iterable<Integer> other = Iterables.concat(
                Ints.asList(4, 5, 6),
                Ints.asList(1, 2, 3));
        // [4, 5, 6, 1, 2, 3]
        System.out.println(other);
        boolean same = Iterables.elementsEqual(concatenated, other);
        // false
        System.out.println(same);

        // 返回iterable的不可变视图
        Iterable<Integer> unmodifiableIterable = Iterables.unmodifiableIterable(concatenated);
        // [1, 2, 3, 4, 5, 6]
        System.out.println(unmodifiableIterable);

        // 限制iterable的元素个数限制给定值
        Iterable<Integer> limitIterable = Iterables.limit(concatenated, 1);
        // [1]
        System.out.println(limitIterable);

        // 获取iterable中唯一的元素，如果iterable为空或有多个元素，则快速失败
        int value = Iterables.getOnlyElement(limitIterable);
        // 1
        System.out.println(value);


        /**
         * 与Collection方法相似的工具方法
         */
        List numbers = Lists.newArrayList(-1, 0);

        Iterables.addAll(numbers, concatenated);
        // [-1, 0, 1, 2, 3, 4, 5, 6]
        System.out.println(numbers);

        boolean contains = Iterables.contains(concatenated, 1);
        // true
        System.out.println(contains);

        boolean removeAll = Iterables.removeAll(numbers, Lists.newArrayList(6, 9));
        // true
        System.out.println(removeAll);
        // [-1, 0, 1, 2, 3, 4, 5]
        System.out.println(numbers);

        numbers = Lists.newArrayList(-1, 0);
        boolean retainAll = Iterables.retainAll(numbers, Lists.newArrayList(0));
        // true
        System.out.println(retainAll);
        // [0]
        System.out.println(numbers);

        int size = Iterables.size(concatenated);
        // 6
        System.out.println(size);

        Integer[] array = Iterables.toArray(concatenated, Integer.class);
        // 1 2 3 4 5 6
        for (Integer integer : array) {
            System.out.print(integer + " ");
        }
        System.out.println();

        boolean isEmpty = Iterables.isEmpty(Lists.newArrayList());
        // true
        System.out.println(isEmpty);

        int one = Iterables.get(concatenated, 1);
        // 2
        System.out.println(one);

        // [1, 2, 3, 4, 5, 6]
        String str = Iterables.toString(concatenated);
        System.out.println(str);

    }

    /**
     * Lists工具类
     */
    public static void lists() {

        List countUp = Ints.asList(1, 2, 3, 4, 5);

        List countDown = Lists.reverse(countUp);
        // {5, 4, 3, 2, 1}
        System.out.println(countDown);


        List<List> parts = Lists.partition(countUp, 2);
        // {{1,2}, {3,4}, {5}}
        System.out.println(countDown);

        /**
         * Lists提供如下静态工厂方法
         */
        List list1 = Lists.newArrayList();
        List list2 = Lists.newArrayList(1, 2);
        List list3 = Lists.newArrayList(Iterables.concat());
        List list4 = Lists.newArrayList(Ints.asList(1).iterator());
        // 分配一个容量为10的数组
        List list5 = Lists.newArrayListWithCapacity(10);
        // 5L + arraySize + (arraySize / 10) = 16 分配一个容量为16的数组
        List list6 = Lists.newArrayListWithExpectedSize(10);

        LinkedList<Integer> linkedList1 = Lists.newLinkedList();
        LinkedList linkedList2 = Lists.newLinkedList(Iterables.concat());

    }

    /**
     * Sets工具类
     */
    public static void sets() {

        /**
         * 集合运算方法
         */

        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        // 交集运算
        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);
        // [two, three, seven]
        System.out.println(intersection);

        // 并集运算
        Sets.SetView<String> union = Sets.union(primes, wordsWithPrimeLength);
        // [two, three, five, seven, one, six, eight]
        System.out.println(union);

        // 差集运算
        Sets.SetView<String> difference = Sets.difference(wordsWithPrimeLength, primes);
        Sets.SetView<String> difference2 = Sets.difference(primes, wordsWithPrimeLength);
        // [one, six, eight]
        System.out.println(difference);
        // [five]
        System.out.println(difference2);

        // 对称差运算
        Sets.SetView<String> symmetricDifference = Sets.symmetricDifference(wordsWithPrimeLength, primes);
        Sets.SetView<String> symmetricDifference2 = Sets.symmetricDifference(primes, wordsWithPrimeLength);
        // [one, six, eight, five]
        System.out.println(symmetricDifference);
        // [five, one, six, eight]
        System.out.println(symmetricDifference2);


        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");

        // 返回所有集合的笛卡儿积
        Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
        // [[gerbil, apple], [gerbil, orange], [gerbil, banana],
        // [hamster, apple], [hamster, orange], [hamster, banana]]
        System.out.println(product);

        // 返回给定集合的所有子集
        Set<Set<String>> animalSets = Sets.powerSet(animals);
        // [] [gerbil] [hamster] [gerbil, hamster]
        animalSets.forEach(v -> System.out.print(v + " "));
        System.out.println();


        /**
         * SetView也实现了Set接口，可直接当作Set使用
         */

        // 对自己做不可变拷贝
        ImmutableSet<String> immutableCopy = intersection.immutableCopy();
        // [two, three, seven]
        System.out.println(immutableCopy);

        // 拷贝进另一个可变集合
        Set<String> set = intersection.copyInto(Sets.newHashSet("one"));
        // [seven, two, three, one]
        System.out.println(set);


        /**
         * 静态工厂方法
         */

        /**
         * HashSet
         */
        // basic
        Set<Integer> hashSet1 = Sets.newHashSet();
        // with elements
        Set<Integer> hashSet2 = Sets.newHashSet(1, 2);
        // from Iterable
        Set<Integer> hashSet3 = Sets.newHashSet(Iterables.concat());
        // from Iterator
        Set<Integer> hashSet4 = Sets.newHashSet(Ints.asList().iterator());
        // with expected size
        Set<Integer> hashSet5 = Sets.newHashSetWithExpectedSize(10);

        /**
         * LinkedHashSet
         */
        // basic
        Set<Integer> linkedHashSet1 = Sets.newLinkedHashSet();
        // from Iterable
        Set<Integer> linkedHashSet2 = Sets.newLinkedHashSet(Iterables.concat());
        // with expected size
        Set<Integer> linkedHashSet3 = Sets.newLinkedHashSetWithExpectedSize(10);

        /**
         * TreeSet
         */
        // basic
        Set<Integer> treeSet1 = Sets.newTreeSet();
        // from Iterable
        Set<Integer> treeSet2 = Sets.newTreeSet(Iterables.concat());

        // rom Iterable
        Set<Integer> treeSet3 = Sets.newTreeSet(Comparator.comparingInt(o -> o));
        treeSet3.addAll(Ints.asList(1, 2, 3));
        // [1, 2, 3]
        System.out.println(treeSet3);

        treeSet3 = Sets.newTreeSet((o1, o2) -> o2 - o1);
        treeSet3.addAll(Ints.asList(1, 2, 3));
        // [3, 2, 1]
        System.out.println(treeSet3);
    }

    /**
     * Maps工具类
     */
    public static void maps() {
        /**
         * uniqueIndex
         * 通常针对的场景是：有一组对象，它们在某个属性上分别有独一无二的值，而我们希望能够按照这个属性值查找对象
         */
        // 比方说，我们有一堆字符串，这些字符串的长度都是独一无二的，而我们希望能够按照特定长度查找字符串
        List<String> strings = Lists.newArrayList("aaa", "bb");
        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(strings, new Function<String, Integer>() {
            public Integer apply(String string) {
                return string.length();
            }
        });

        /**
         * difference
         * 用来比较两个Map以获取所有不同点
         */
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        // 两个Map中都有的映射项，包括匹配的键与值
        Map<String, Integer> entriesInCommon = diff.entriesInCommon();
        // {b=2}
        System.out.println(entriesInCommon);

        // 键相同但是值不同值映射项
        Map<String, MapDifference.ValueDifference<Integer>> entriesDiffering = diff.entriesDiffering();
        // {c=(3, 4)}
        System.out.println(entriesDiffering);

        // 键只存在于左边Map的映射项
        Map<String, Integer> entriesOnlyOnLeft = diff.entriesOnlyOnLeft();
        // {a=1}
        System.out.println(entriesOnlyOnLeft);

        // 键只存在于右边Map的映射项
        Map<String, Integer> entriesOnlyOnRight = diff.entriesOnlyOnRight();
        // {d=5}
        System.out.println(entriesOnlyOnRight);

        /**
         * BiMap
         * 既提供键到值的映射，也提供值到键的映射，是双向Map
         */
        // Maps.synchronizedBiMap();

    }

    public static void main(String[] args) {
        iterable();
        lists();
        sets();
        maps();

    }


}