/*
 *  This file is part of the Haven & Hearth game client.
 *  Copyright (C) 2009 Fredrik Tolf <fredrik@dolda2000.com>, and
 *                     Björn Johannessen <johannessen.bjorn@gmail.com>
 *
 *  Redistribution and/or modification of this file is subject to the
 *  terms of the GNU Lesser General Public License, version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  Other parts of this source tree adhere to other copying
 *  rights. Please see the file `COPYING' in the root directory of the
 *  source tree for details.
 *
 *  A copy the GNU Lesser General Public License is distributed along
 *  with the source tree of which this file is a part in the file
 *  `doc/LPGL-3'. If it is missing for any reason, please see the Free
 *  Software Foundation's website at <http://www.fsf.org/>, or write
 *  to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 *  Boston, MA 02111-1307 USA
 */

package haven.glsl;

import java.util.*;

public abstract class PreOp extends Expression {
    public final Expression op;

    public PreOp(Expression op) {
	this.op = op;
    }

    public PreOp process(Context ctx) {
	try {
	    return(this.getClass().getConstructor(Expression.class).newInstance(op.process(ctx)));
	} catch(NoSuchMethodException e) {
	    throw(new Error(e));
	} catch(InstantiationException e) {
	    throw(new Error(e));
	} catch(IllegalAccessException e) {
	    throw(new Error(e));
	} catch(java.lang.reflect.InvocationTargetException e) {
	    throw(new Error(e));
	}
    }

    public abstract String form();

    public void output(Output out) {
	out.write("(");
	out.write(form());
	op.output(out);
	out.write(")");
    }

    public static class Neg extends PreOp {public String form() {return("-");} public Neg(Expression op) {super(op);}}
}
