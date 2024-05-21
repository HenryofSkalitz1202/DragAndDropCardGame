package com.hbd.Kartu;

public abstract class Kartu {
    private final String nama;

    public Kartu(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return this.nama;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nama == null) ? 0 : nama.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Kartu other = (Kartu) obj;
        if (nama == null) {
            if (other.nama != null)
                return false;
        } else if (!nama.equals(other.nama))
            return false;
        return true;
    }

}
