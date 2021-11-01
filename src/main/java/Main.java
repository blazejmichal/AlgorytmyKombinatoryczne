import algorithms.Algorithm8;
import com.google.common.collect.Lists;

public class Main {

  public static void main(String[] args) {

//    List<List<Integer>> algorithm9Result = new Algorithm9().run(
//        Lists.newArrayList(1, 2, 3)
//    );
//    new Algorithm9().runVersion3(3);
    new Algorithm8(Lists.newArrayList(0, 1, 2, 3)).PERM(3);
  }

}
