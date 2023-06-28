<template>
    <div class="bill-list">
        <h1>账单列表</h1>
        <div class="cz">
            <div class="add">
                <el-button class="btn btn-success" @click="dialogAddFormVisible = true">+添加账单</el-button>
            </div>
            <div class="filter">
                <p><input type="radio" value="全部" name="type" @click="filterBills('all')" checked="checked">全部</p>
                <p><input type="radio" value="收入" name="type" @click="filterBills(1)">收入</p>
                <p><input type="radio" value="支出" name="type" @click="filterBills(2)">支出</p>
            </div>
        </div>
        <table>
            <thead>
                <tr>
                    <th width="100px">收支</th>
                    <th width="180px">金额</th>
                    <th width="150px">类别</th>
                    <th width="380px">备注</th>
                    <th width="220px">创建时间</th>
                    <th width="220px">更新时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="bill in filteredBills" :key="bill.id">
                    <td>{{ bill.typeName }}</td>
                    <td>{{ bill.money }}</td>
                    <td>{{ bill.categoryName }}</td>
                    <td>{{ bill.notes }}</td>
                    <td>{{ bill.createTime }}</td>
                    <td>{{ bill.updateTime }}</td>
                    <td>
                        <div><button type="button" class="btn btn-primary" @click="editBill(bill.id)">编辑</button></div>
                        <div><button type="button" class="btn btn-danger" @click="deleteBill(bill.id)">删除</button></div>
                    </td>
                </tr>
            </tbody>
        </table>
        <el-dialog title="添加账单信息" :visible.sync="dialogAddFormVisible" class="dialog">
            <el-form @submit.prevent="addBill">
                <el-form-item label="金额：" :label-width="formLabelWidth">
                    <el-input v-model="newBill.money" autocomplete="off" required></el-input>
                </el-form-item>
                <el-form-item label="备注：" :label-width="formLabelWidth">
                    <el-input v-model="newBill.notes" autocomplete="off" required></el-input>
                </el-form-item>
                <el-form-item label="收支：" :label-width="formLabelWidth">
                    <el-select v-model="newBill.type_id" required>
                        <el-option label="收入" value="1"></el-option>
                        <el-option label="支出" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="类别：" :label-width="formLabelWidth">
                    <el-select v-model="newBill.category_id" placeholder="请选择账单类别" required>
                        <el-option v-for="category in categories" :key="category.id" :value="category.id"
                            :label="category.categoryName"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogAddFormVisible = false">取 消</el-button>
                <el-button type="submit" class="btn-success" @click="addBill">添加</el-button>
            </div>
        </el-dialog>

        <!-- 编辑账单 -->
        <el-dialog title="编辑账单信息" :visible.sync="dialogUpdateFormVisible" class="dialog">
            <el-form v-if="editingBill" @submit.prevent="addBill">
                <el-form-item label="金额：" :label-width="formLabelWidth">
                    <el-input v-model="newBill.money" autocomplete="off" required></el-input>
                </el-form-item>
                <el-form-item label="备注：" :label-width="formLabelWidth">
                    <el-input v-model="newBill.notes" autocomplete="off" required></el-input>
                </el-form-item>
                <el-form-item label="收支：" :label-width="formLabelWidth">
                    <el-select v-model="newBill.type_id" required>
                        <el-option label="收入" value="1"></el-option>
                        <el-option label="支出" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="类别：" :label-width="formLabelWidth">
                    <el-select v-model="newBill.category_id" placeholder="请选择账单类别" required>
                        <el-option v-for="category in categories" :key="category.id" :value="category.id"
                            :label="category.categoryName"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogUpdateFormVisible = false">取 消</el-button>
                <el-button type="submit" class="btn-success" @click="addBill">添加</el-button>
            </div>
        </el-dialog>
    </div>
</template>
  
<script>
import { fetchBills, fetchCategories, addBill, updateBill, deleteBill } from '@/api/bill';

export default {
    data() {
        return {
            bills: [],
            types: [],
            categories: [],
            filter: 'all',
            dialogAddFormVisible: false,
            dialogUpdateFormVisible: false,
            newBill: {
                money: '',
                notes: '',
                type_id: '1',
                category_id: ''
            },
            editingBill: null,
            formLabelWidth: '120px'
        };
    },

    mounted() {
        this.fetchBills();
        this.fetchCategories();
    },

    //筛选类型
    computed: {
        filteredBills() {
            if (this.filter === 'all') {
                return this.bills;
            } else {
                return this.bills.filter(bill => bill.typeId === this.filter);
            }
        },
    },

    methods: {
        //获取账单列表
        fetchBills() {
            fetchBills()
                .then(response => {
                    this.bills = response.data.data;
                    console.log("账单列表：", response.data.data);
                })
                .catch(error => {
                    console.error(error);
                });
        },

        //收支检索
        filterBills(typeId) {
            this.filter = typeId;
        },

        //获取账单用途类别
        fetchCategories() {
            fetchCategories()
                .then(response => {
                    this.categories = response.data.data;
                })
                .catch(error => {
                    console.error(error);
                });
        },

        //添加账单
        addBill() {
            addBill(this.newBill)
                .then(response => {
                    this.fetchBills();
                    this.$message.success("添加成功！");
                    console.log("添加成功，账单信息：", response.data);
                })
                .catch(error => {
                    this.$message.error("添加失败");
                    console.error(error);
                    this.dialogAddFormVisible = true;
                });
        },

        // 删除账单
        deleteBill(id) {
            deleteBill({id})
                .then(response => {
                    this.$message.success("删除成功！");
                    console.log(response.data);
                    this.fetchBills();
                })
                .catch(error => {
                    console.error(error);
                });
        }
    },
};
</script>
  
<style src="../../assets/css/bill.css" scoped></style>