package com.z.utils;

public class Page {

    private int nowPageNumber = 1;// 当前页码

    private int sizePerPage = 10;// 每页记录数

    private int allSize = 0;// 总记录数

    private int allPageNumber = 1;// 总页数

    private boolean hasPrevious = true;// 是否上页

    private int previousPageNumber = 1;// 上页页码

    private boolean hasNext = true;// 是否下页

    private int nextPageNumber = 1;// 下页页码

    private int nowStartNumber = 0;// 开始记录数

    private int nowEndNumber = 20;// 结束记录数

    private int pageNumListSize = 9;// 页码列表数量

    private int[] pageNumList;// 页码列表

    public Page() { }

    public Page(int nowPageNumber, int allSize, int sizePerPage) {
        if (nowPageNumber > 0) {
            this.nowPageNumber = nowPageNumber;
        }

        if (allSize > 0) {
            this.allSize = allSize;
        }

        if (sizePerPage > 0) {
            this.sizePerPage = sizePerPage;
        }

        this.allPageNumber = this.allSize / this.sizePerPage;
        if (this.allSize % this.sizePerPage > 0) {
            this.allPageNumber++;
        }
        if (this.allPageNumber == 0) {
            this.allPageNumber = 1;
        }

        this.previousPageNumber = this.nowPageNumber - 1;
        if (this.previousPageNumber < 1) {
            this.previousPageNumber = 1;
            this.hasPrevious = false;
        }
        if (previousPageNumber == 0) {
            previousPageNumber = 1;
        }

        this.nextPageNumber = this.nowPageNumber + 1;
        if (this.nextPageNumber > allPageNumber) {
            this.nextPageNumber = allPageNumber;
            this.hasNext = false;
        }
        if (nextPageNumber == 0) {
            nextPageNumber = 1;
        }

        this.nowStartNumber = (this.nowPageNumber - 1) * this.sizePerPage;

        this.nowEndNumber = this.nowPageNumber * this.sizePerPage;
        if (this.nowEndNumber > this.allSize) {
            this.nowEndNumber = this.allSize;
        }

        if (this.allPageNumber >= 1) {
            if (this.pageNumListSize > this.allPageNumber) {
                this.pageNumListSize = this.allPageNumber;
            }
            int start = 1;
            int end = this.allPageNumber;
            int i = this.pageNumListSize / 2;
            if (end > this.pageNumListSize) {
                if (this.nowPageNumber > i + 1) {
                    start = this.nowPageNumber - i;
                    end = this.nowPageNumber + i;
                    if (this.nowPageNumber > (allPageNumber - i)) {
                        start = allPageNumber - this.pageNumListSize + 1;
                        end = allPageNumber;
                    }
                } else {
                    start = 1;
                    end = this.pageNumListSize;
                }
            }
            if (start < 1) {
                start = 1;
            }
            if (end > this.allPageNumber) {
                end = this.allPageNumber;
            }
            this.pageNumList = new int[this.pageNumListSize];
            int count = 0;
            for (int x = start; x <= end; x++) {
                this.pageNumList[count++] = x;
            }
        } else {
            this.pageNumList = new int[0];
        }

    }

    public Page(int nowPageNumber, int allSize, int sizePerPage, int pageNumListSize) {
        if (nowPageNumber > 0) {
            this.nowPageNumber = nowPageNumber;
        }

        if (allSize > 0) {
            this.allSize = allSize;
        }

        if (sizePerPage > 0) {
            this.sizePerPage = sizePerPage;
        }

        if (pageNumListSize > 0) {
            this.pageNumListSize = pageNumListSize;
        }

        this.allPageNumber = this.allSize / this.sizePerPage;
        if (this.allSize % this.sizePerPage > 0) {
            this.allPageNumber++;
        }
        if (this.allPageNumber == 0) {
            this.allPageNumber = 1;
        }

        this.previousPageNumber = this.nowPageNumber - 1;
        if (this.previousPageNumber < 1) {
            this.previousPageNumber = 1;
            this.hasPrevious = false;
        }
        if (previousPageNumber == 0) {
            previousPageNumber = 1;
        }

        this.nextPageNumber = this.nowPageNumber + 1;
        if (this.nextPageNumber > allPageNumber) {
            this.nextPageNumber = allPageNumber;
            this.hasNext = false;
        }
        if (nextPageNumber == 0) {
            nextPageNumber = 1;
        }

        this.nowStartNumber = (this.nowPageNumber - 1) * this.sizePerPage;

        this.nowEndNumber = this.nowPageNumber * this.sizePerPage;
        if (this.nowEndNumber > this.allSize) {
            this.nowEndNumber = this.allSize;
        }

        if (this.allPageNumber >= 1) {
            if (this.pageNumListSize > this.allPageNumber) {
                this.pageNumListSize = this.allPageNumber;
            }
            int start = 1;
            int end = this.allPageNumber;
            int i = this.pageNumListSize / 2;
            if (end > this.pageNumListSize) {
                if (this.nowPageNumber > i + 1) {
                    start = this.nowPageNumber - i;
                    end = this.nowPageNumber + i;
                    if (this.nowPageNumber > (allPageNumber - i)) {
                        start = allPageNumber - this.pageNumListSize + 1;
                        end = allPageNumber;
                    }
                } else {
                    start = 1;
                    end = this.pageNumListSize;
                }
            }
            if (start < 1) {
                start = 1;
            }
            if (end > this.allPageNumber) {
                end = this.allPageNumber;
            }
            this.pageNumList = new int[this.pageNumListSize];
            int count = 0;
            for (int x = start; x <= end; x++) {
                this.pageNumList[count++] = x;
            }
        } else {
            this.pageNumList = new int[0];
        }

    }

    public Page(int nowStartNumber, int sizePerPage) {
        if (sizePerPage > 0) {
            this.sizePerPage = sizePerPage;
        }
        this.nowStartNumber = nowStartNumber;
        this.nowEndNumber = nowStartNumber + sizePerPage;
        
    }

    public int getNowPageNumber() {
        return nowPageNumber;
    }

    public void setNowPageNumber(int nowPageNumber) {
        this.nowPageNumber = nowPageNumber;
    }

    public int getSizePerPage() {
        return sizePerPage;
    }

    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;
    }

    public int getAllSize() {
        return allSize;
    }

    public void setAllSize(int allSize) {
        this.allSize = allSize;
    }

    public int getAllPageNumber() {
        return allPageNumber;
    }

    public void setAllPageNumber(int allPageNumber) {
        this.allPageNumber = allPageNumber;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public int getPreviousPageNumber() {
        return previousPageNumber;
    }

    public void setPreviousPageNumber(int previousPageNumber) {
        this.previousPageNumber = previousPageNumber;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getNextPageNumber() {
        return nextPageNumber;
    }

    public void setNextPageNumber(int nextPageNumber) {
        this.nextPageNumber = nextPageNumber;
    }

    public int getNowStartNumber() {
        return nowStartNumber;
    }

    public void setNowStartNumber(int nowStartNumber) {
        this.nowStartNumber = nowStartNumber;
    }

    public int getNowEndNumber() {
        return nowEndNumber;
    }

    public void setNowEndNumber(int nowEndNumber) {
        this.nowEndNumber = nowEndNumber;
    }

    public int getPageNumListSize() {
        return pageNumListSize;
    }

    public void setPageNumListSize(int pageNumListSize) {
        this.pageNumListSize = pageNumListSize;
    }

    public int[] getPageNumList() {
        return pageNumList;
    }

    public void setPageNumList(int[] pageNumList) {
        this.pageNumList = pageNumList;
    }

}
