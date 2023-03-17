fn main() {
    let mut arr: Vec<i32> = vec![99, 19, 9, 7, 11, 3, 3, 2, 2, 1];
    println!("Before sort {arr:?}");

    heap_sort(&mut arr);

    println!("After sort: {arr:?}");
}

fn heap_sort(arr: &mut Vec<i32>) {
    let mut end_index = arr.len() - 1;

    swap(arr, 0, end_index);
    end_index -= 1;
    maxify_heap(arr, 0, end_index);
    loop {
        swap(arr, 0, end_index);
        end_index -= 1;
        maxify_heap(arr, 0, end_index);
        if end_index == 1 {
            break;
        }
    }
}

fn maxify_heap(arr: &mut Vec<i32>, start_idx: usize, end_idx: usize) {
    let left_index = 2 * start_idx + 1;
    let right_index = 2 * start_idx + 2;

    let mut largest_index: usize;

    if left_index < end_idx && arr[left_index] > arr[start_idx] {
        largest_index = left_index;
    } else {
        largest_index = start_idx;
    }

    if right_index < end_idx && arr[right_index] > arr[largest_index] {
        largest_index = right_index;
    }

    if largest_index != start_idx {
        swap(arr, start_idx, largest_index);
        maxify_heap(arr, largest_index, end_idx);
    }
}

fn swap(arr: &mut Vec<i32>, idx1: usize, idx2: usize) {
    let temp = arr[idx1];
    arr[idx1] = arr[idx2];
    arr[idx2] = temp;
}
