/**
 * Copyright 2013-2015 JueYue (qrb.jueyue@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package afterturn.easypoi.excel.entity.params;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class SunXungExportParams extends ExportParams {

    // 副标题排列样式
    private HorizontalAlignment secondTitleAlign;

    public HorizontalAlignment getSecondTitleAlign() {
        return secondTitleAlign;
    }

    public void setSecondTitleAlign(HorizontalAlignment secondTitleAlign) {
        this.secondTitleAlign = secondTitleAlign;
    }
}
