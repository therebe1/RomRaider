/*
 *
 * Enginuity Open-Source Tuning, Logging and Reflashing
 * Copyright (C) 2006 Enginuity.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

package enginuity.logger.ecu.comms.query;

import enginuity.logger.ecu.definition.EcuData;
import static enginuity.util.HexUtil.asHex;
import static enginuity.util.ParamChecker.checkNotNull;

public final class EcuQueryImpl implements EcuQuery {
    private final EcuData ecuData;
    private final LoggerCallback callback;
    private final byte[] bytes;
    private final String hex;

    public EcuQueryImpl(EcuData ecuData, LoggerCallback callback) {
        checkNotNull(ecuData, callback);
        this.ecuData = ecuData;
        this.callback = callback;
        bytes = ecuData.getAddress().getBytes();
        hex = asHex(bytes);
    }

    public String[] getAddresses() {
        return ecuData.getAddress().getAddresses();
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getHex() {
        return hex;
    }

    public void setResponse(byte[] response) {
        callback.callback(ecuData.getSelectedConvertor().convert(response));
    }

    public boolean equals(Object object) {
        return object instanceof EcuQueryImpl && getHex().equals(((EcuQueryImpl) object).getHex());
    }

    public int hashCode() {
        return getHex().hashCode();
    }

    public String toString() {
        return "0x" + getHex();
    }
}