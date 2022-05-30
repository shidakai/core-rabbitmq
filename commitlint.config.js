const chalk = require('chalk');
const msgPaths = process.env.GIT_PARAMS;
let msg = require('fs')
  .readFileSync(msgPaths, 'utf-8')
  .trim();
  

const commitRE = /^(revert: )?(feat|fix|docs|style|refactor|perf|test|chore|ci|build|types)(\(.+\))?: .{1,50}/;
//const commitRE = /^(:\w{3,50}:.{3,30})/;
if (msg.includes('Merge branch')) {
  // 自动合并不加验证
} else {
  console.log(`提交信息是:${chalk.green(msg)}`);
  if (!commitRE.test(msg)) {
    console.log(msg);
    console.error(
      `  ${chalk.bgRed.white(' ERROR ')} ${chalk.red(`提交信息不合法.`)}\n\n` +
        chalk.red(
          `  type must be one of [feat(新特性),fix(修订bug),perf(性能加强),docs(文档),style(样式),refactor(重构),test(测试),chore(构建过程)]. 例如:\n\n`
        ) +
        `    ${chalk.green(`feat(projectmanager):  修订了编码规则`)}\n\n` +
        `    ${chalk.green( `详细信息多行\n`)}\n` +
        `    Closes #1，#2`
    );
    process.exit(1);
  } else {
    console.log(
      `${chalk.green('提交校验通过')}`
    );
  }
}