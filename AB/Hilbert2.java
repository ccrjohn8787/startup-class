class HilbertCurve{
  public HilbertCurve(){};
  public int getNum(int d, int x, int y) {
    return helper(d, x, y, 0);
  }
  public int helper(int d, int x, int y, int plus) {
    if (d == 1) {
      if (x == 0 && y == 0) {
        return 1 + plus;
      } else if (x == 0 && y == 1) {
        return 2 + plus;
      } else if (x == 1 && y == 0) {
        return 4 + plus;
      } else if (x == 1 && y == 1) {
        return 3 + plus;
      }
      // wrong value
      return -1;
    }
    
    int size = (int)Math.pow(2, d - 1);
    // 2rd part
    if (x < size && y >= size) {
      return helper(d - 1, x, y - size, plus + size * size);
    } else if (x >= size && y >= size) {
      return helper(d - 1, x - size, y - size, plus + 2 * size * size);
    } else if (x < size && y < size) {
      return helper(d - 1, y, x, plus);
    }
    return helper(d - 1, size - 1 - y, size * 2 - 1 - x, plus + 3 * size * size );
  }
}
