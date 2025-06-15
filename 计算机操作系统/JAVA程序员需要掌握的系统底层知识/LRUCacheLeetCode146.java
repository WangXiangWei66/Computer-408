public class LRUCacheLeetCode146 {

    //本代码基于LRU缓存策略，在容量满时有限淘汰最近未使用的数据
    class Node {
        //定义双向链表节点：双向链表支持 O (1) 时间复杂度的节点插入和删除操作。
        int key;
        int value;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public Node[] map;//快速定位键对应的节点
    public int count, capacity;//当前缓存中的元素数量和缓存的最大容量
    public Node head, tail;

    public LRUCacheLeetCode146(int capacity) {
        this.capacity = capacity;
        count = 0;

        map = new Node[10_000 + 1];

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;//指向最近访问节点
        tail.prev = head;//指向最久未访问节点

        head.prev = null;
        tail.next = null;
    }

    public void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        return;
    }

    public void addToHead(Node node) {
        node.next = head.next;//新节点next指向原头节点的下一个
        node.next.prev = node;
        node.prev = head;

        head.next = node;

        return;
    }

    //获取键对应的值，并将该节点标记为最近访问
    public int get(int key) {
        if (map[key] != null) {
            Node node = map[key];//通过哈希表快速定位节点

            int nodeVal = node.value;

            deleteNode(node);
            addToHead(node);
            return nodeVal;
        } else {
            return -1;
        }
    }

    //添加并更新键值对，并维护LRU顺序
    public void put(int key, int value) {
        if (map[key] != null) {
            Node node = map[key];

            node.value = value;

            deleteNode(node);
            addToHead(node);
        } else {

            Node node = new Node(key, value);

            map[key] = node;

            if (count < capacity) {
                count++;
                addToHead(node);
            } else {
                map[tail.prev.key] = null;//删除最久没有被使用的元素
                deleteNode(tail.prev);
                addToHead(node);
            }
        }
        return;
    }
}
