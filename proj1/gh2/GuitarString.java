package gh2;

import deque.Deque;
import deque.ArrayDeque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.常数。不要换。如果你好奇，关键字 final
     *  意味着这些值不能在运行时更改。我们将在周五的讲座中讨论这个话题和其他话题。 */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 0.996; // energy decay factor

    /* Buffer for storing sound data.用于存储声音数据的缓冲区。 */
    private Deque<Double> buffer = new ArrayDeque<>();

    /* Create a guitar string of the given frequency. 创建给定频率的吉他弦。 */
    public GuitarString(double frequency) {

        int capacity = (int) Math.round(SR / frequency);
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise.
     * 通过用白噪声替换缓冲器来拨动吉他弦。 */
    public void pluck() {
//               between -0.5 and 0.5. You can get such a number by using:
//               double r = Math.random() - 0.5;
//
//               Make sure that your random numbers are different from each
//               other. This does not mean that you need to check that the numbers
//               are different from each other. It means you should repeatedly call
//  Math.random() - 0.5 to generate new random numbers for each array index.
//        在 -0.5 和 0.5 之间。您可以使用以下方法获得这样的数字：
//        双 r = Math.random() - 0.5;
//        确保您的随机数彼此不同。这并不意味着您需要检查数字是否彼此不同。这意味着你应该反复打电话
//        Math.random() - 0.5 为每个数组索引生成新的随机数。
        for (int i = 0; i < buffer.size(); i++) {
            double r = Math.random() - 0.5;
            buffer.removeFirst();
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     * 通过执行 Karplus-Strong 算法的一次迭代将仿真推进一个时间步长。
     */
    public void tic() {
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().
        // 两者的平均值乘以 DECAY 因子。
        //不要调用 StdAudio.play()。****
        double newItem = ((buffer.get(0) + buffer.get(1)) / 2.0)  * DECAY;
        buffer.removeFirst();
        buffer.addLast(newItem);
    }

    /* Return the double at the front of the buffer.
     * 返回缓冲区前面的双精度数。  */
    public double sample() {
        return buffer.get(0);
    }
}
