public class CircularQueue<T> {
    private int top = -1;
    private int base = 0;
    private T[] data;

    @SuppressWarnings("unchecked")
    public CircularQueue(int size) {
        data = (T[]) new Object[size];
    }

    public void add(T element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        if (top == -1) {
            top = 0;
            base = 0;
        } else {
            top = move(top);
        }
        data[top] = element;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T element = data[base];
        if (base == top) {
            base = 0;
            top = -1;
        } else {
            base = move(base);
        }
        return element;
    }

    public void clear() {
        top = -1;
        base = 0;
        data = (T[]) new Object[data.length];
    }

    public boolean isFull() {
        return move(top) == base && top != -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private int move(int position) {
        return (position + 1) % data.length;
    }

    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>(5);

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println(queue.remove());
        System.out.println(queue.remove());

        queue.add(6);
        queue.add(7);

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
