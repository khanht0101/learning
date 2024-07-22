export const sumSelectedSizes = (items, selectedIds) => {
    const sum = items.reduce((total, item) => {
        if (selectedIds.includes(item.Id)) {
            return total + item.Size;
        }
        return total;
    }, 0);
    return sum;
};

export const isValidURL = (url) => {
    try {
        new URL(url);
        return true;
    } catch (error) {
        return false;
    }
};
