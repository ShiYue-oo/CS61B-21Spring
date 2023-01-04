package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 使用合成器包复制弹拨吉他弦音的客户端
 */

public class GuitarHeroLite {
    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C 创建两个吉他弦，用于音乐会 A 和 C*/
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);

        while (true) {

            /* check if the user has typed a key; if so, process it 检查用户是否输入了密钥；如果是，处理它 */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                }
            }

            /* compute the superposition of samples 计算样本的叠加*/
            double sample = stringA.sample() + stringC.sample();

            /* play the sample on standard audio 在标准音频上播放样本 */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step 将每根吉他弦的模拟推进一步*/
            stringA.tic();
            stringC.tic();
        }
    }
}

