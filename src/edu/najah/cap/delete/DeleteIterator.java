    package edu.najah.cap.delete;

    import java.util.Iterator;
    import java.util.List;

    public class DeleteIterator<T> implements Iterator<T> {
        private final List<T> list;
        private int currentIndex = 0;

        public DeleteIterator(List<T> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < list.size();
        }

        public void deleteCurrent() {
            if (currentIndex >= 0 && currentIndex < list.size()) {
                list.remove(currentIndex);
            }
        }

        public boolean isDataFound() {
            if (list == null) {
                return false;
            }
            return list.size() > 0;
        }

        @Override
        public T next() {
            return list.get(currentIndex++);
        }
    }